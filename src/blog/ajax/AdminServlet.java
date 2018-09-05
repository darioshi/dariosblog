package blog.ajax;

import blog.service.ArticleService;
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
        switch (op) {
            case "delete_article":
                String article_id = request.getParameter("article_id");
                int code = 0;
                String msg="";
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
        }
        response.getWriter().println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
