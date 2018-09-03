package blog.ajax;

import net.sf.json.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "UploadPicServlet", urlPatterns = {"/UploadPic"})
public class UploadPicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建Json对象
        JSONObject jsonObject = new JSONObject();

        //文件保存路径
        String savePath = this.getServletContext().getRealPath("/upload");
        File saveFileDir = new File(savePath);
        if(!saveFileDir.exists()) {
            saveFileDir.mkdirs();
        }

        //临时文件保存路径
        String tmpPath = this.getServletContext().getRealPath("/upload/tmp");
        File tmpPathDir = new File(tmpPath);
        if(!tmpPathDir.exists()) {
            tmpPathDir.mkdirs();
        }

        String message = "";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 10);
        factory.setRepository(tmpPathDir);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(1024 * 1024 * 3);

        try {
            List items = upload.parseRequest(request);
            if (items.size() != 0) {
                FileItem item = (FileItem) items.get(0);
                String fileName = item.getName();
                fileName = fileName.substring(fileName.indexOf("\\") + 1);
                if (item.getSize() > 1024 * 1024 * 3) {
                    message = message + "文件：" + fileName + "，上传文件大小超过限制大小：" + upload.getFileSizeMax();
                    jsonObject.put("success", 0);
                    jsonObject.put("message", message);
                } else {
                    String saveFileName = this.makeFileName(fileName);
                    InputStream is = item.getInputStream();
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFileName);
                    byte buffer[] = new byte[1024];
                    int len = 0;
                    while ((len = is.read(buffer)) > 0) {
                        out.write(buffer, 0, len);
                    }

                    out.close();
                    is.close();
                    item.delete();
                    message = message + "文件：" + fileName + "上传成功";

                    String url = "/Blog/upload/" + saveFileName;
                    jsonObject.put("success", 1);
                    jsonObject.put("message", message);
                    jsonObject.put("url", url);
                }
            }
        } catch (FileUploadException e) {
            message = message + "上传文件大小超过限制";
            jsonObject.put("success", 0);
            jsonObject.put("message", message);
            e.printStackTrace();
        }
        response.getWriter().print(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private String makeFileName(String fileName) {
        // 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;

    }
}
