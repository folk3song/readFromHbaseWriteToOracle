package com.sirc.hbase;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(
        basePackages = "com.sirc.hbase.*",
        annotationClass = Repository.class
)
public class HbaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbaseApplication.class, args);
    }

}
