package com.zxwang.zexx.config;

import com.zxwang.zexx.interceptor.MyAnInterceptor;
import com.zxwang.zexx.interceptor.MyInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器的配置类，用于注册和生效拦截器
 */
@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 在springboot容器中放入拦截器
     * @return
     */
    @Bean
    public MyInterceptor myInterceptor(){
        return  new MyInterceptor();
    }

    @Bean
    public MyAnInterceptor myAnInterceptor(){
        return  new MyAnInterceptor();
    }

    /**
     * 注册拦截器，并且拦截指定的路由，否则不生效
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册拦截器");
        registry.addInterceptor(myInterceptor()).addPathPatterns("/**");
        /**
         * 此处也可以实现自定义注解匹配
         */
        registry.addInterceptor(myAnInterceptor()).addPathPatterns("/**");
    }
}
