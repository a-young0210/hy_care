package com.example.hycare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableCaching
@SpringBootApplication
@CrossOrigin(origins = "http://18.183.4.163:8080",allowedHeaders = "*")
public class HyCareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyCareApplication.class, args);

    }

}
