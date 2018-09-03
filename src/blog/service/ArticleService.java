package blog.service;

import blog.dao.ArticleDao;
import blog.daoImpl.ArticleDaoImpl;
import blog.model.Article;
import blog.utils.ArticleUtils;
import blog.utils.FailException;
import blog.utils.Form2Bean;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ArticleService {

    private ArticleDao articleDao;

    private static ArticleService instance;

    private ArticleService() {
        articleDao = ArticleDaoImpl.getInstance();
    }

    public static ArticleService getInstance() {
        if(instance == null) {
            try {
                instance = new ArticleService();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     *  获取文章总数
     * @return
     */
    public int getCount() {
        return articleDao.getCount();
    }

    public List getArticle() {
        List<Article> articleList = articleDao.getArticle();
        if(articleList != null) {
            ArticleUtils.cutContent(articleList);
            ArticleUtils.cutTime(articleList);
        }
        return articleList;
    }

    public List<Article> getVisitRank() {
        return articleDao.getVisitRank();
    }

    public Article getArticleById(int id) {
        Article article = articleDao.getArticleById(id);
        if(article != null) {
            ArticleUtils.cutTime(article);
        }
        return article;
    }

    public Article getPreArticle(int id) {
        return articleDao.getPreArticle(id);
    }

    public Article getNextArticle(int id) {
        return articleDao.getNextArticle(id);
    }

    public Article addArticle(HttpServletRequest request, int sort_id) {
        Article article = null;
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        try {
            article = Form2Bean.articleForm2Bean(request,sort_id);
        } catch (FailException e) {
            e.printStackTrace();
        }
        if(article == null) return article;
        return articleDao.addArticle(article);
    }

    public int starArticle(int articleId) {
        return articleDao.starArticle(articleId);
    }

    public void addVisit(int articleId) {
        articleDao.addVisit(articleId);
    }
}
