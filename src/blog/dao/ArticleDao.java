package blog.dao;

import blog.model.Article;

import java.util.List;

public interface ArticleDao {

    /**
     * 获取总文章数
     * @return
     */
    int getCount();

    /**
     * 获取文章列表
     * @return
     */
    List<Article> getArticle();

    /**
     * 阅读排行
     * @return
     */
    List<Article> getVisitRank();

    /**
     * 获取文章详细
     * @return
     */
    Article getArticleById(int id);

    /**
     * 根据分类ID获取文章列表
     * @param sortId
     * @return
     */
    List<Article> getArticleBySortId(int sortId);

    /**
     * 根据标签ID获取文章列表
     * @param tagId
     * @return
     */
    List<Article> getArticleByTagId(int tagId);

    /**
     * 获取前一篇文章
     * @param id
     * @return
     */
    Article getPreArticle(int id);

    /**
     * 获取下一篇文章
     * @param id
     * @return
     */
    Article getNextArticle(int id);

    /**
     * 新增文章
     * @return
     */
    Article addArticle(Article a);

    /**
     * 获取最新文章
     * @return
     */
    Article getLastArticle();

    /**
     * 点赞文章
     * @return
     */
    int starArticle(int articleId);

    /**
     * 评论文章
     * @param articleId
     * @return
     */
    int commentArticle(int articleId);

    /**
     * 删除评论
     * @param articleId
     * @return
     */
    int desComment(int articleId);

    /**
     * 增加文章访问量
     * @param articleId
     */
    void addVisit(int articleId);

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    boolean delArticle(int articleId);

    /**
     * 更新文章
     * @param articleId
     * @return
     */
    Article updateArticle(int articleId, Article article);
}
