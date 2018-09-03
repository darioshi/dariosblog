package blog.servlet;

import blog.service.SortService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SortServlet", urlPatterns = {"/SortServlet"})
public class SortServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SortService sortService = SortService.getInstance();
        String get = request.getParameter("get");
        request.setAttribute("sort_article_map",sortService.getSortAndArticle(get));
        request.getRequestDispatcher("/page/sort.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
