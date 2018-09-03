package blog.dao;

import blog.model.ArticleTag;
import blog.model.Tag;

import java.util.List;

public interface TagDao {

    /**
     * 获取标签数量
     * @return
     */
    int getCount();

    /**
     * 获取标签和数量
     * @return
     */
    List getTagAndCont();

    /**
     * 获取文章标签
     * @param articleId
     * @return
     */
    List<Tag> getTagsByArticleId(int articleId);

    /**
     * 获取全部标签
     * @return
     */
    List<Tag> getAllTag();

    /**
     * 根据ID获取标签
     * @return
     */
    Tag getTagById(int tagId);

    /**
     * 增加标签
     * @param name
     * @return
     */
    Tag addTag(String name);

    /**
     * 获取最新标签
     * @return
     */
    Tag getLastTag();

    /**
     * 根据标签名获取标签
     * @return
     */
    Tag getTagByName(String name);

    /**
     * 添加文章分类
     * @param articleId
     * @param tagId
     * @return
     */
    int addArticleTag(int articleId, int tagId);
}
