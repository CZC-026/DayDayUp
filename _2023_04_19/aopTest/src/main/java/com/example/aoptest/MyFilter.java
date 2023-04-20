package com.example.aoptest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Filter是Servlet组件，可以拦截所有的请求，可以拿到http的request和response，适用于对整个应用程序的处理
 * 可以对请求头，请求参数，请求URL等进行过滤和操作，可进行安全检查，字符编码转换，日志记录等
 */

@Component
@Order(-1)
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter doFilter before...");
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("MyFilter doFilter after");
    }

    @Override
    public void destroy() {
        System.out.println("MyFilter destroy");
    }
}