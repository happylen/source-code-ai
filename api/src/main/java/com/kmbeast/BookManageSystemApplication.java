package com.kmbeast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 */
@MapperScan("com.kmbeast.mapper")
@SpringBootApplication
public class BookManageSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookManageSystemApplication.class, args);
    }
}
