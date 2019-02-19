package com.sdata.ecommerce;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @author nedli
 */
@SpringBootApplication
@MapperScan("com.sdata.ecommerce.mapper")
@Import(FdfsClientConfig.class)
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class ProductSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductSampleApplication.class, args);
    }
}
