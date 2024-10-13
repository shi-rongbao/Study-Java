package com.shirongbao.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/*")
public class Filter1 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter1 before doFilter invoke");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("filter1 after doFilter invoke");
    }
}
