package com.github.wb322;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.github.wb322.dao"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run (WebApplication.class, args);
    }

}
