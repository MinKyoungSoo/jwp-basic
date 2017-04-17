package next.filter;

import next.web.CreateUserServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class MyServletFilter implements Filter{

    private static final Logger log = LoggerFactory.getLogger(CreateUserServlet.class);
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        
        filterChain.doFilter(servletRequest, servletResponse);
        
        long endTime = System.currentTimeMillis();
        long lTime = endTime - startTime;
        log.debug("TIME : " + lTime + "(ms)");
    }

    @Override
    public void destroy() {

    }
}
