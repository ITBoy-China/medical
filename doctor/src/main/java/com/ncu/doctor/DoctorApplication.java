package com.ncu.doctor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan(basePackages = "com.ncu.doctor.mapper")
@EnableEurekaClient
public class DoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorApplication.class, args);
    }

}
