package com.sdata.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.rest.RestClientAutoConfiguration;

@SpringBootApplication(exclude = RestClientAutoConfiguration.class)
public class ElasticsearchSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchSampleApplication.class, args);
    }

}
