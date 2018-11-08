package com.yiwenyin.integral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IntegralApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegralApplication.class, args);
    }
}
