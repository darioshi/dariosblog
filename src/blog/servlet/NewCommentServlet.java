package blog.servlet;

import blog.service.CommentService;
import blog.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "NewCommentServlet", urlPatterns = {"/NewCommentServlet"})
public class NewCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookie_name = "comment_article_" + request.getParameter("article_id");

        boolean repeat = false;

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(cookie_name)) {
                    repeat = true;
                    break;
                }
            }
        }

        //返回对象信息
        String comment_info;
        if (!repeat) {
            CommentService commentService = CommentService.getInstance();
            int result = commentService.addComment(request);
            if(result != 0) {
                comment_info = "<span style='color:green'>评论成功</span>";
            } else {
                comment_info = "<span style='color:red'>评论失败，请稍后再试</span>";
            }
        } else {
            comment_info = "<span style='color:red'>评论失败，请稍后再试</span>";
        }

        Cookie c = new Cookie(cookie_name, DateUtils.getFormatDate(new Date()));
        c.setMaxAge(60 * 60);
        c.setPath("/Blog");
        response.addCookie(c);

        request.setAttribute("comment_info", comment_info);
        request.getRequestDispatcher("/ArticleServlet?id="+request.getParameter("article_id")).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/ArticleServlet?id="+request.getParameter("article_id")).forward(request, response);
    }
}
