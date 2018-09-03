package blog.filter;

import blog.db.VisitorDB;
import blog.utils.DateUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//统计网站访问量
@WebFilter(filterName = "VisitorFilter", urlPatterns = {"/*"})
public class VisitorFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        synchronized (this) {
            Cookie[] cookies = request.getCookies();
            boolean visited = false;
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("blog_visitor")) {
                    visited = true;
                    break;
                }
            }

            if (!visited) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        VisitorDB.visit(request);

                        // 发送新的cookie
                        Cookie c = new Cookie("blog_visitor", DateUtils.getFormatDate(new Date()));
                        // cookie生命周60分钟
                        c.setMaxAge(60 * 60);
                        c.setPath("/Blog");
                        response.addCookie(c);
                    }
                });
                t.start();
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
