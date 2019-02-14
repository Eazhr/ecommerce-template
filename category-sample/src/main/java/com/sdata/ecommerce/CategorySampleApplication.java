package com.sdata.ecommerce;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.sdata.ecommerce.mapper")
public class CategorySampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategorySampleApplication.class, args);
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}

