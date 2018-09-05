package blog.service;

import blog.dao.ArticleDao;
import blog.dao.TagDao;
import blog.daoImpl.ArticleDaoImpl;
import blog.daoImpl.TagDaoImpl;
import blog.model.Article;
import blog.model.ArticleTag;
import blog.model.Tag;
import blog.utils.ArticleUtils;
import blog.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagService {

    private TagDao tagDao;

    private static TagService instance;

    private TagService() {
        tagDao = TagDaoImpl.getInstance();
    }

    public static TagService getInstance() {
        if(instance == null) {
            try {
                instance = new TagService();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * 获取标签数量
     * @return
     */
    public int getCount() {
        return tagDao.getCount();
    }

    public List getTagAndCont() {
        return tagDao.getTagAndCont();
    }

    public List<Tag> getTagsByArticleId(int articleId) {
        return tagDao.getTagsByArticleId(articleId);
    }

    public Map getTagAndArticle(String get) {
        Map map = new HashMap();
        ArticleDao articleDao = ArticleDaoImpl.getInstance();
        if(StringUtils.isEmpty(get) || get.equals("all")) {
            List<Tag> tagList = tagDao.getAllTag();
            if(tagList != null) {
                for (Tag tag : tagList) {
                    List<Article> articleList = articleDao.getArticleByTagId(tag.getId());
                    ArticleUtils.cutTime(articleList);
                    map.put(tag, articleList);
                }
            }
        }else {
            Tag tag = tagDao.getTagById(Integer.parseInt(get));
            if(tag != null) {
                List<Article> articleList = articleDao.getArticleByTagId(tag.getId());
                ArticleUtils.cutTime(articleList);
                map.put(tag, articleList);
            }
        }
        return map;
    }

    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    public List<Tag> addTag(HttpServletRequest request) {
        List<Tag> tagList = new ArrayList<>();
        String tags_str = request.getParameter("tags").trim();
        String[] tags = tags_str.split(" ");
        for(String t : tags) {
            Tag tag = tagDao.getTagByName(t);
            if(tag == null)
                tag = tagDao.addTag(t);
            tagList.add(tag);
        }
        return tagList;
    }

    public boolean addArticleTag(int articleId, List<Tag> tagList) {
        //先删除文章所有标签关联
        tagDao.deleteArticleTag(articleId);
        for(Tag tag : tagList) {
            ArticleTag articleTag = tagDao.getArticleTag(articleId,tag.getId());
            if(articleTag == null)
                tagDao.addArticleTag(articleId,tag.getId());
        }
        return true;
    }

}
