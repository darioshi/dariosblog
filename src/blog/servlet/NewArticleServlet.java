package blog.servlet;

import blog.model.Article;
import blog.model.Sort;
import blog.model.Tag;
import blog.service.ArticleService;
import blog.service.SortService;
import blog.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NewArticleServlet", urlPatterns = {"/NewArticleServlet"})
public class NewArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SortService sortService = SortService.getInstance();
        Sort sort = sortService.addSort(request);
        int sort_id = sort==null ? 0:sort.getId();
        ArticleService articleService = ArticleService.getInstance();
        Article article = articleService.addArticle(request,sort_id);
        if (article != null) {
            TagService tagService = TagService.getInstance();
            List<Tag> tagList = tagService.addTag(request);
            tagService.addArticleTag(article.getId(),tagList);
        }
        request.setAttribute("article", article);
        request.getRequestDispatcher("/admin/result.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/admin/result.jsp").forward(request, response);
    }
}
