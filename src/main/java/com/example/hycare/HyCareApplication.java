package com.example.hycare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HyCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyCareApplication.class, args);

    }

}
