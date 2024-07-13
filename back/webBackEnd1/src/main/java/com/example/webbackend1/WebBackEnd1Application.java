package com.example.webbackend1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.webbackend1.mapper")
@ComponentScan({"com.example"})
public class WebBackEnd1Application {

    public static void main(String[] args) {
        SpringApplication.run(WebBackEnd1Application.class, args);
    }
}
