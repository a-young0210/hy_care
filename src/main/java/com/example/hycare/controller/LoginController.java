package com.example.hycare.controller;

import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {
    @PostMapping("/api/auth/google/complete")
    public void loginGooglePost(@RequestBody Object val) {
        log.info(">>> Google Login API(POST)");
    }
}
