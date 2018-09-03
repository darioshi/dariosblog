package blog.servlet;

import blog.model.*;
import blog.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ArticleServlet", urlPatterns = {"/ArticleServlet"})
public class ArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if(idStr != null && !idStr.equals("")) {
            int id = Integer.parseInt(idStr);
            ArticleService articleService = ArticleService.getInstance();
            Article article = articleService.getArticleById(id);
            if(article != null) {
                request.setAttribute("article",article);
                //获取文章分类
                SortService sortService = SortService.getInstance();
                Sort sort = sortService.getSortById(article.getSort_id());
                request.setAttribute("sort",sort);
                //获取文章作者
                UserService userService = UserService.getInstance();
                User author = userService.getUserById(article.getUser_id());
                request.setAttribute("author",author);
                //获取文章标签
                TagService tagService = TagService.getInstance();
                List<Tag> tagList = tagService.getTagsByArticleId(article.getId());
                request.setAttribute("tags_list",tagList);
                //获取文章评论
                CommentService commentService = CommentService.getInstance();
                List<Comment> commentList = commentService.getCommentByArticleId(article.getId());
                request.setAttribute("comment_list",commentList);
                //获取前一篇文章
                request.setAttribute("pre_article",articleService.getPreArticle(article.getId()));
                //获取下一篇文章
                request.setAttribute("next_article",articleService.getNextArticle(article.getId()));

                request.getRequestDispatcher("/page/article.jsp").forward(request, response);
            }else {
                getServletContext().getRequestDispatcher("/page/main.jsp").forward(request,response);
            }
        }else {
            getServletContext().getRequestDispatcher("/page/main.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
