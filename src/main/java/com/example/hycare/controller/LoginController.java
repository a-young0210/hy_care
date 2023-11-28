package com.example.hycare.controller;

import com.example.hycare.dto.MemberDto;
import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {
    @Value("${server.host.api}")
    private String baseUrl;

    @PostMapping("/api/auth/google/complete")
    public void loginGooglePost(@RequestBody LinkedHashMap<String, String> loginParam) {
        log.info(">>> Google Login API(POST)");

        // Dto value setting
        MemberDto memberDto = new MemberDto();
        memberDto.setMemName(loginParam.get("name"));
        memberDto.setEmail(loginParam.get("email"));
        memberDto.setIsDoctor(loginParam.get("loginDiv"));

        // DB 저장 API 호출
        String url = baseUrl + "/member/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(memberDto, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResultEntity> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                ResultEntity.class);
    }
}
