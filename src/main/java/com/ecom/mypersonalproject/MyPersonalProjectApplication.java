package com.ecom.mypersonalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MyPersonalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPersonalProjectApplication.class, args);
    }

}
