package blog.servlet;

import blog.model.Sort;
import blog.model.User;
import blog.service.SortService;
import blog.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddServlet", urlPatterns = {"/AddServlet"})
public class AddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null) {
            // 获取分类
            SortService sortService = SortService.getInstance();
            List<Sort> sortList = sortService.getAllSort();
            request.setAttribute("all_sort", sortList);
            // 获取标签
            TagService tagService = TagService.getInstance();
            List all_tag = tagService.getAllTag();
            request.setAttribute("all_tag", all_tag);

            request.getRequestDispatcher("/admin/add.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/login.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
