package com.zxwang.zexx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan // 启用@WebFilter注解
public class InterceptorStudyDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterceptorStudyDemoApplication.class, args);
    }

}
