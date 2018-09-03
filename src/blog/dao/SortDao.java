package blog.dao;

import blog.model.Sort;

import java.util.List;
import java.util.Map;

public interface SortDao {

    /**
     * 获取分类数量
     * @return
     */
    int getCount();

    /**
     * 获取分类数量
     * @return
     */
    List getSortAndCount();

    /**
     * 获取分类信息
     * @param id
     * @return
     */
    Sort getSortById(int id);

    /**
     * 获取所有分类
     * @return
     */
    List<Sort> getAllSort();

    /**
     * 添加分类
     * @param s
     * @return
     */
    Sort addSort(Sort s);

    /**
     * 获取最新分类
     * @return
     */
    Sort getLastSort();

    /**
     * 根据分类名获取分类
     * @return
     */
    Sort getSortByName(String name);
}
