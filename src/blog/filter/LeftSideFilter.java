package blog.filter;

import blog.service.ArticleService;
import blog.service.SortService;
import blog.service.TagService;
import blog.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 加载左侧栏数据 filter
 */
@WebFilter(filterName = "LeftSideFilter", urlPatterns = {"/*"})
public class LeftSideFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        if(request.getRequestURL().indexOf("login.html") == -1) {
            SortService sortService = SortService.getInstance();
            ArticleService articleService = ArticleService.getInstance();
            TagService tagService = TagService.getInstance();
            //左侧公共栏
            // 初始化侧边栏 日志、分类、标签的个数
            request.setAttribute("article_number", articleService.getCount());
            request.setAttribute("sort_number", sortService.getCount());
            request.setAttribute("tags_number", tagService.getCount());

            // 初始化文章列表
            request.setAttribute("article_list", articleService.getArticle());
            //初始化分类列表
            request.setAttribute("sort_list", sortService.getSortAndCount());
            // 初始化获取标签
            request.setAttribute("tag_list", tagService.getTagAndCont());

            // 阅读排行
            request.setAttribute("visit_rank", articleService.getVisitRank());
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
