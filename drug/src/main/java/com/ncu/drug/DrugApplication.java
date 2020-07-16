package com.ncu.drug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.ncu.drug.mapper")
@org.mybatis.spring.annotation.MapperScan(basePackages = "com.ncu.drug.mapper")
@EnableDiscoveryClient //开启客户端发现
public class DrugApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrugApplication.class, args);
    }

}
