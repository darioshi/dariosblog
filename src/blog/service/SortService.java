package blog.service;

import blog.dao.ArticleDao;
import blog.dao.SortDao;
import blog.daoImpl.ArticleDaoImpl;
import blog.daoImpl.SortDaoImpl;
import blog.model.Article;
import blog.model.Sort;
import blog.utils.ArticleUtils;
import blog.utils.Form2Bean;
import blog.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortService {
    private SortDao sortDao;

    private static SortService instance;

    private SortService() {
        sortDao = SortDaoImpl.getInstance();
    }

    public static SortService getInstance() {
        if(instance == null) {
            try {
                instance = new SortService();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * 获取分类数量
     * @return
     */
    public int getCount() {
        return sortDao.getCount();
    }

    public List getSortAndCount() {
        return sortDao.getSortAndCount();
    }

    public Sort getSortById(int id) {
        return sortDao.getSortById(id);
    }

    public Map getSortAndArticle(String get) {
        Map map = new HashMap();
        ArticleDao articleDao = ArticleDaoImpl.getInstance();
        if(StringUtils.isEmpty(get) || get.equals("all")) {
            List<Sort> sortList = sortDao.getAllSort();
            if(sortList != null) {
                for (Sort sort : sortList) {
                    List<Article> articleList = articleDao.getArticleBySortId(sort.getId());
                    ArticleUtils.cutTime(articleList);
                    map.put(sort, articleList);
                }
            }
        }else {
            Sort sort = sortDao.getSortById(Integer.parseInt(get));
            if(sort != null) {
                List<Article> articleList = articleDao.getArticleBySortId(sort.getId());
                ArticleUtils.cutTime(articleList);
                map.put(sort, articleList);
            }
        }
        return map;
    }

    public List<Sort> getAllSort() {
        return sortDao.getAllSort();
    }

    public Sort addSort(HttpServletRequest request) {
        Sort sort = null;
        String sort_name = request.getParameter("sort");
        sort = sortDao.getSortByName(sort_name);
        if(sort != null) return sort;
        sort = Form2Bean.sortForm2bean(request);
        if(sort == null) return sort;
        return sortDao.addSort(sort);
    }

    public boolean updateSort(int sortId, String sortName) {
        return sortDao.updateSort(sortId,sortName);
    }

    public boolean delSort(int sortId) {
        return sortDao.deleteSort(sortId);
    }
}
