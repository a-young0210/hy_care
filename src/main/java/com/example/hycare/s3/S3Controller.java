package com.example.hycare.s3;

import com.example.hycare.dto.HycareDto;
import com.example.hycare.chatGPT.ChatGPTDto;
import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Service s3Service;

    @Value("${server.host.api}")
    private String baseUrl;

    // S3 저장
    @PostMapping("/s3-save")
    public ResponseEntity<ResultEntity> uploadFile(@RequestBody String path) {
        try {
            File file = new File(path + "/chatGPTDto.json");
            String s3Url = s3Service.upload(file, "static");

            HycareDto hycareDto = new HycareDto();
            hycareDto.setDiagText(s3Url);

            // DB 저장 controller 호출
            String url = baseUrl + "/hy-care/save";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity<>(hycareDto, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ResultEntity> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    httpEntity,
                    ResultEntity.class);

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