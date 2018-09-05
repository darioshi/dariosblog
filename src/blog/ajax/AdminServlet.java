package blog.ajax;

import blog.service.ArticleService;
import blog.service.SortService;
import blog.service.TagService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        JSONObject jsonObject = new JSONObject();
        int code = 0;
        String msg="";
        switch (op) {
            case "delete_article":
                String article_id = request.getParameter("article_id");
                if(article_id == null || article_id.equals("")) {
                    msg = "params error";
                }else {
                    int articleId = Integer.parseInt(article_id);
                    ArticleService articleService = ArticleService.getInstance();
                    if(articleService.delArticle(articleId)) {
                        code = 1;
                        msg = "success";
                    }else {
                        msg = "error";
                    }
                }
                jsonObject.put("code",code);
                jsonObject.put("msg",msg);
                break;
            case "sort_update":
                String sort_id = request.getParameter("sort_id");
                String sort_name = request.getParameter("sort_name");
                if (sort_id == null || sort_name == null || sort_id.equals("")) {
                    msg = "params error";
                }else {
                    int sortId = Integer.parseInt(sort_id);
                    SortService sortService = SortService.getInstance();
                    if (sortService.updateSort(sortId,sort_name)) {
                        code = 1;
                        msg = "success";
                    }else {
                        msg = "error";
                    }
                }
                jsonObject.put("code",code);
                jsonObject.put("msg",msg);
                break;
            case "sort_delete":
                String sortId = request.getParameter("sort_id");
                if(sortId == null || sortId.equals("")) {
                    msg = "params error";
                }else {
                    int sortid = Integer.parseInt(sortId);
                    SortService sortService = SortService.getInstance();
                    if(sortService.delSort(sortid)) {
                        ArticleService articleService = ArticleService.getInstance();
                        articleService.delArticleBySortId(sortid);
                        code = 1;
                        msg = "success";
                    }else {
                        msg = "error";
                    }
                }
                jsonObject.put("code",code);
                jsonObject.put("msg",msg);
                break;
            case "tag_update":
                String tag_id = request.getParameter("tag_id");
                String tag_name = request.getParameter("tag_name");
                if (tag_id == null || tag_name == null || tag_id.equals("")) {
                    msg = "params error";
                }else {
                    int tagId = Integer.parseInt(tag_id);
                    TagService tagService = TagService.getInstance();
                    if (tagService.updateTag(tagId,tag_name)) {
                        code = 1;
                        msg = "success";
                    }else {
                        msg = "error";
                    }
                }
                jsonObject.put("code",code);
                jsonObject.put("msg",msg);
                break;
            case "tag_delete":
                String tagId = request.getParameter("tag_id");
                if(tagId == null || tagId.equals("")) {
                    msg = "params error";
                }else {
                    int tagid = Integer.parseInt(tagId);
                    TagService tagService = TagService.getInstance();
                    if(tagService.delTag(tagid)) {
                        code = 1;
                        msg = "success";
                    }else {
                        msg = "error";
                    }
                }
                jsonObject.put("code",code);
                jsonObject.put("msg",msg);
                break;
        }
        response.getWriter().println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
