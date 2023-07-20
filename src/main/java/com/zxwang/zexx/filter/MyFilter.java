package com.zxwang.zexx.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter doFilter");
        System.out.println("Request: " + request);
        chain.doFilter(request, response);
        System.out.println("Response: " + response);
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter destroy");
    }
}
