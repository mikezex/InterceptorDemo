package com.zxwang.zexx.interceptor;

import com.zxwang.zexx.annotation.MyAnnotation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义注解拦截器
 */
public class MyAnInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // some logic
        if (handler instanceof HandlerMethod) {
            MyAnnotation needLogin = ((HandlerMethod) handler).getMethodAnnotation(MyAnnotation.class);
            if (needLogin == null){
                System.out.println("无注解请求，逻辑处理");
            }else{
                System.out.println("有MyAnnotation注解的请求，逻辑处理");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
