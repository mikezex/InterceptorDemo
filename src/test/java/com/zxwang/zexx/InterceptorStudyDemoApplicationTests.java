package com.zxwang.zexx;

import com.zxwang.zexx.pojo.UserBO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InterceptorStudyDemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void testUser() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/1", String.class);
        System.out.println(response.getBody());
    }


    @Test
    void test() {
        UserBO bo = new UserBO();
        bo.setId("1");
        bo.setName("zex");
        ResponseEntity<UserBO> response = restTemplate.postForEntity("/user/test",bo, UserBO.class);
        System.out.println(response.getBody());
    }

    // testAspect
    @Test
    void testAspect() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/testAspect", String.class);
        System.out.println(response.getBody());
    }



}
