package com.example.aoptest;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
    //三个作用的时机：
    //preHandle:是处理请求之前
    //postHandle：是处理请求之后，渲染视图之前
    //afterCompletion:是渲染视图之后
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor preHandle... ");
        //进行权限校验
        boolean hasPermission = true;
        if(!hasPermission){
            //没有权限 直接跳转回登录页面
            response.sendRedirect("login");
            return false;
        }
        return true;
    }




    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor afterCompletion");
        //log
        logger.debug("拦截完成");

    }
}
