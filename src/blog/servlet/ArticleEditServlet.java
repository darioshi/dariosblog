package blog.servlet;

import blog.model.Article;
import blog.model.Sort;
import blog.model.Tag;
import blog.model.User;
import blog.service.ArticleService;
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

@WebServlet(name = "ArticleEditServlet", urlPatterns = {"/ArticleEditServlet"})
public class ArticleEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String article_id = request.getParameter("id");
        if(user != null && article_id != null && !article_id.equals("")) {
            int id = Integer.parseInt(article_id);
            ArticleService articleService = ArticleService.getInstance();
            Article article = articleService.getArticleById(id);
            if(article != null) {
                request.setAttribute("article", article);
                SortService sortService = SortService.getInstance();
                Sort sort = sortService.getSortById(article.getSort_id());
                request.setAttribute("sort", sort);
                TagService tagService = TagService.getInstance();
                List<Tag> tagList = tagService.getTagsByArticleId(article.getId());
                request.setAttribute("tagList", tagList);
                // 获取分类
                List<Sort> sortList = sortService.getAllSort();
                request.setAttribute("all_sort", sortList);
                // 获取标签
                List all_tag = tagService.getAllTag();
                request.setAttribute("all_tag", all_tag);
            }else {
                request.getRequestDispatcher("/LoginServlet").forward(request, response);
            }
            request.getRequestDispatcher("/admin/edit.jsp").forward(request, response);
        }else {
            request.getRequestDispatcher("/LoginServlet").forward(request, response);
        }
    }
}
