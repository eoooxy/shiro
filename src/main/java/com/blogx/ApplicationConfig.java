package com.blogx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * author： xueyuan
 * date  ： 2017-08-14 下午4:13
 */

@SpringBootApplication
//@MapperScan(basePackages = "com.blogx.mapper")
public class ApplicationConfig {

    public static void main(String[] args) {
        //SpringApplication application = new SpringApplication();
        SpringApplication.run(ApplicationConfig.class);
    }
}
