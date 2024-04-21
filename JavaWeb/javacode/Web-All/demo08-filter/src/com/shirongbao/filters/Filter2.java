package com.shirongbao.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/*")
public class Filter2 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter2 before doFilter invoke");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("filter2 after doFilter invoke");
    }
}
