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
        String id = request.getParameter("id");
        Article article = null;
        if(id != null) { //更新
            article = articleService.updateArticle(request, sort_id,Integer.parseInt(id));
            request.setAttribute("msg", "更新成功！");
        }else {
            article = articleService.addArticle(request, sort_id);
            request.setAttribute("msg", "新增成功！");
        }
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
