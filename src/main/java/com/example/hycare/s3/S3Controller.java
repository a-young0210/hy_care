package com.example.hycare.s3;

import com.example.hycare.Service.DiagnosisService;
import com.example.hycare.dto.DiagnosisDto;
import com.example.hycare.chatGPT.ChatGPTDto;
import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Service s3Service;
    private final DiagnosisService diagnosisService;

    @Value("${server.host.api}")
    private String baseUrl;

    // S3 저장
    @PostMapping("/s3-save/{uuid}")
    public ResponseEntity<ResultEntity> uploadFile(@RequestBody String path, @PathVariable String uuid) {
        try {
            File file = new File(path + "/chatGPTDto.json");
            String s3Url = s3Service.upload(file, "static", uuid);

            DiagnosisDto diagnosisDto = new DiagnosisDto();
            diagnosisDto.setDiagLink(s3Url);

            diagnosisService.saveDiagnosis(diagnosisDto, uuid);

            /**
             * Diagnosis DB 저장 완료 -> member diag_id 추가를 위해 api 호출*/

        } catch (Exception e) { return new ResponseEntity(HttpStatus.BAD_REQUEST); }
        return new ResponseEntity(HttpStatus.OK);
    }

    // S3에서 객체 url 조회
    @GetMapping("/s3-find/{id}")
    public ResultEntity<ChatGPTDto> findS3(@PathVariable("id") String id) {
        String fileName = "static/" + id + "_chatGPT.json";
        ChatGPTDto result;
        try {
            result = s3Service.findFile(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResultEntity<>(result);
    }
}