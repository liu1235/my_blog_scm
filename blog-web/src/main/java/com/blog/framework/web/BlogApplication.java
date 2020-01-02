package com.blog.framework.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author liuzw
 */
@MapperScan(basePackages = "com.blog.framework.mapper")
@SpringBootApplication(scanBasePackages = "com.blog.framework")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
