package com.sdata.ecommerce;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author nedli
 */
@SpringBootApplication
@MapperScan("com.sdata.ecommerce.mapper")
public class ProductSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductSampleApplication.class, args);
    }
}
