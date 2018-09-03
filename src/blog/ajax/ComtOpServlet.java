package blog.ajax;

import blog.service.CommentService;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ComtOpServlet", urlPatterns = {"/ComtOpServlet"})
public class ComtOpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommentService commentService = CommentService.getInstance();
        JSONObject jsonObject = new JSONObject();
        String op = request.getParameter("op");
        switch (op) {
            case "del_com" :
                int commentId = Integer.parseInt(request.getParameter("comment_id"));
                int articleId = Integer.parseInt(request.getParameter("article_id"));
                int res = commentService.delComment(articleId,commentId);
                if(res != -1)
                    jsonObject.put("msg","success");
                else
                    jsonObject.put("msg","fail");
                break;
            case "star_com" :
                int commentId2 = Integer.parseInt(request.getParameter("comment_id"));
                boolean repeat = false;
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("star_com_"+commentId2)) {
                        repeat = true;
                        break;
                    }
                }
                if(!repeat) {
                    int new_star = commentService.starComment(commentId2);
                    jsonObject.put("msg", "success");
                    jsonObject.put("new_star", new_star);
                    Cookie cookie = new Cookie("star_com_"+commentId2,System.currentTimeMillis()+"");
                    cookie.setMaxAge(15*60);
                    cookie.setPath("/Blog");
                    response.addCookie(cookie);
                }
                break;
            case "diss_com" :
                int commentId3 = Integer.parseInt(request.getParameter("comment_id"));
                boolean repeat1 = false;
                Cookie[] cookies1 = request.getCookies();
                for (Cookie cookie : cookies1) {
                    if(cookie.getName().equals("diss_com_" + commentId3)) {
                        repeat1 = true;
                        break;
                    }
                }
                if(!repeat1) {
                    int new_diss = commentService.dissComment(commentId3);
                    jsonObject.put("msg", "success");
                    jsonObject.put("new_diss", new_diss);

                    Cookie cookie = new Cookie("diss_com_" + commentId3 , System.currentTimeMillis() + "");
                    cookie.setMaxAge(15 * 60);
                    cookie.setPath("/Blog");
                    response.addCookie(cookie);
                }
                break;
            default:
                break;
        }
        response.getWriter().println(jsonObject);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
