package blog.servlet;

import blog.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TagsServlet", urlPatterns = {"/TagsServlet"})
public class TagsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TagService tagService = TagService.getInstance();
        String get = request.getParameter("get");
        request.setAttribute("tag_map", tagService.getTagAndArticle(get));

        request.getRequestDispatcher("/page/tags.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
