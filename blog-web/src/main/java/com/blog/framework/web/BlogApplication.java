package com.blog.framework.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * @author liuzw
 */
@EnableAsync
@MapperScan(basePackages = "com.blog.framework.mapper")
@SpringBootApplication(scanBasePackages = "com.blog.framework")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
