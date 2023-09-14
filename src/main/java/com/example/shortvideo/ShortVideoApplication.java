package com.example.shortvideo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.shortvideo.mapper")
public class ShortVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShortVideoApplication.class, args);
    }

}
