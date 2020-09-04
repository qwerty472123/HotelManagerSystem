package com.myexample.bootwithmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.myexample.bootwithmybatis.mapper")
public class BootWithMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootWithMybatisApplication.class, args);
    }

}
