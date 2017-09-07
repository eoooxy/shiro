package com.blogx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * author： xueyuan
 * date  ： 2017-08-14 下午4:13
 */

@SpringBootApplication
public class ApplicationConfig extends SpringBootServletInitializer {

    public static void main(String[] args) {
        //SpringApplication application = new SpringApplication();
        SpringApplication.run(ApplicationConfig.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationConfig.class);
    }
}
