package com.example.hycare.controller;

import com.example.hycare.Service.MemberService;
import com.example.hycare.dto.MemberDto;
import com.example.hycare.entity.ApiResult;
import com.example.hycare.entity.Member;
import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Value("${server.host.api}")
    private String baseUrl;

    // 회원가입 및 로그인
    @PostMapping("/login")
    public ResultEntity memLogin(@RequestBody MemberDto memberDto) {
        ResultEntity result = new ResultEntity();
        try {
            if(memValid(memberDto.getEmail())) {
                // 회원이 존재하지 않으면 DB 저장
                String[] email = memberDto.getEmail().split("@");
                memberDto.setMemName(email[0]);
                memberService.saveMember(memberDto);

                // 회원가입 및 로그인 성공 반환
                result.setCode("0000");
                result.setMessage("Join and Sign up Success");
            } else {
                // 로그인 성공 반환
                result.setCode("0000");
                result.setMessage("Sign up Success");
            }
            return result;
        } catch (Exception e) {
            return new ResultEntity(e);
        }
    }

    // email로 회원 찾기
    @GetMapping("/find-email/{email}")
    public ResultEntity findMemByEmail(@PathVariable("email") String email) {
        ResultEntity result = new ResultEntity(ApiResult.SUCCESSS);
        try {
            MemberDto member = memberService.findByEmail(email);
            if(member.getEmail() == null) { // email에 해당하는 회원이 없는 경우
               result.setCode(ApiResult.USER_NOT_FOUND.getCode());
               result.setMessage(ApiResult.USER_NOT_FOUND.getMessage());
               result.setData(null);
                return result;
            }
            result.setData(member);
        } catch (Exception e) {
            result = new ResultEntity(e);
        }
        return result;
    }

    // 진료 완료 -> member의 diagId 업데이트
    /**
     * 수정해야함*/
    @PostMapping("update/{id}")
    public ResultEntity updateDiagId(@PathVariable("id") int id, @RequestBody String diagnosisId) {
        ResultEntity result = new ResultEntity(ApiResult.SUCCESSS);
        MemberDto memberDto = new MemberDto();
        try {
            memberDto = memberService.findById(id);
            if(memberDto != null) {     // 회원 존재
                // 기존 diagId에 추가
                List<String> diagIdList = memberDto.getDiagId();
                diagIdList.add(diagnosisId);
                memberDto.setDiagId(diagIdList);
                memberService.addDiagId(memberDto);
                result.setData(memberDto);
            } else {    // 회원 존재X
                result.setCode(ApiResult.USER_NOT_FOUND.getCode());
                result.setMessage(ApiResult.USER_NOT_FOUND.getMessage());
                result.setData(null);
                return result;
            }
        } catch (Exception e) {
            return new ResultEntity(e);
        }
        return result;
    }


    // 회원이 존재하는지 validation
    public Boolean memValid(String email) {
        MemberDto member = memberService.findByEmail(email);
        if(member.getEmail() == null) {   // 회원 존재X
            return true;
        }
        return false;
    }
}
