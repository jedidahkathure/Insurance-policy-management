package com.example.policymanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PolicyManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PolicyManagementServiceApplication.class, args);
    }

}
