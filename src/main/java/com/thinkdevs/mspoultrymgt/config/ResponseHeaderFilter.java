package com.thinkdevs.mspoultrymgt.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/api/*")
@Component
public class ResponseHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.addHeader(
                "X-Content-Type-Options", "nosniff");
        httpServletResponse.addHeader(
                "X-Frame-Options", "DENY");
        httpServletResponse.addHeader(
                "Content-Security-Policy", "default-src");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
