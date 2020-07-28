package com.demon.springbootthymeleaf;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootThymeleafApplicationTests {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
    }

    @Test
    void myPassword() {
        String result = passwordEncoder.encode("123456");
        System.out.println(result);
        System.out.println(passwordEncoder.matches("123456", result));
    }
}
