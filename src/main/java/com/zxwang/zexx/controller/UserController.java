package com.zxwang.zexx.controller;

import com.zxwang.zexx.annotation.MyAnnotation;
import com.zxwang.zexx.pojo.UserBO;
import com.zxwang.zexx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 测试拦截器 Interceptor、过滤器 Filter、控制通知器 ControllerAdvice 的先后顺序
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Object getUser(@PathVariable Long id) {
        // 模拟抛出异常
        throw new RuntimeException("User not found");
    }

    /**
     * 自定义注解，在拦截器与ControllerAdvice中 添加相关处理操作
     * @return
     */
    @PostMapping ("test")
    @MyAnnotation
    @ResponseBody
    public UserBO testAnnotation(@RequestBody UserBO bo) {
        return bo;
    }

    @GetMapping("testAspect")
    public String testAspect(){
       return iUserService.test("1");
    }


}
