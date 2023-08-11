package com.example.hycare.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.example.hycare.chatGPT.ChatGPTDto;
import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;

import static com.example.hycare.entity.ApiResult.SUCCESSS;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Service s3Service;

    // S3 저장
    @PostMapping("/s3-save")
    public ResponseEntity<ResultEntity> uploadFile(@RequestBody String path) {
        try {
            File file = new File(path + "/chatGPTDto.json");
            s3Service.upload(file, "static");
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