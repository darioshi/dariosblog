package blog.ajax;

import blog.service.ArticleService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ArticleStarServlet", urlPatterns = {"/ArticleStarServlet"})
public class ArticleStarServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String article_id = request.getParameter("article_id");

        JSONObject jsonObject = new JSONObject();
        boolean repeat = false;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("star_article_" + article_id)) {
                jsonObject.put("msg","failed");
                repeat = true;
            }
        }

        if (!repeat) {
            ArticleService articleService = ArticleService.getInstance();
            int new_star = articleService.starArticle(Integer.parseInt(article_id));

            jsonObject.put("msg", "success");
            jsonObject.put("new_star", new_star);

            Cookie cookie = new Cookie("star_article_" + article_id , System.currentTimeMillis() + "");
            cookie.setMaxAge(15*60);
            cookie.setPath("/Blog");
            response.addCookie(cookie);
        }

        response.getWriter().println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
