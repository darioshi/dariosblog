package blog.filter;

import blog.service.ArticleService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "ArticleFilter", urlPatterns = {"/ArticleServlet"})
public class ArticleFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        boolean repeat = false;
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String id = request.getParameter("id");
        if(id != null && !id.equals("")) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("article_visit_" + id)) {
                        repeat = true;
                        break;
                    }
                }
                if (!repeat) {
                    int articleId = Integer.parseInt(id);
                    ArticleService articleService = ArticleService.getInstance();
                    articleService.addVisit(articleId);
                    Cookie cookie = new Cookie("article_visit_" + id, System.currentTimeMillis() + "");
                    cookie.setPath("/Blog");
                    cookie.setMaxAge(60 * 5);
                    response.addCookie(cookie);
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
