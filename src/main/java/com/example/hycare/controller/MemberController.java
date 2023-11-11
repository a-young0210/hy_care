package com.example.hycare.controller;

import com.example.hycare.Service.MemberService;
import com.example.hycare.dto.MemberDto;
import com.example.hycare.entity.ApiResult;
import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Value("${server.host.api}")
    private String baseUrl;

    @PostMapping("/save")
    public ResultEntity saveMember(@RequestBody MemberDto memberDto) {
        try {

        } catch (Exception e) {
            return new ResultEntity(ApiResult.FAIL);
        }
        return new ResultEntity(ApiResult.SUCCESSS);
    }
}
