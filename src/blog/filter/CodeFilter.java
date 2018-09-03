package blog.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CodeFilter", urlPatterns = { "/*" })
public class CodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rp = (HttpServletResponse) response;
        rq.setCharacterEncoding("utf-8");
        rp.setCharacterEncoding("utf-8");
        rp.setContentType("text/html;charset=utf-8");

        rp.setHeader("Cache-Control", "no-cache");
        rp.setHeader("Pragma", "no-cache");
        rp.setDateHeader("expires", -1);

        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
