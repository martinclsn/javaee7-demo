package se.jee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@WebFilter(urlPatterns = {"/*"})
public class ThreadNamingServletFilter implements Filter {

    private static final AtomicLong threadId = new AtomicLong(0);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {


        Thread currentThread = Thread.currentThread();
        String threadPrefix = "Thread ";
        if(!currentThread.getName().startsWith(threadPrefix)) {
            currentThread.setName(threadPrefix + threadId.incrementAndGet());
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
