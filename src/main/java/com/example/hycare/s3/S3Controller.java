package com.example.hycare.s3;

import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RequiredArgsConstructor
@RestController
public class S3Controller {
    private final S3Uploader s3Uploader;

    @PostMapping("/s3-save")
    public ResponseEntity<ResultEntity> uploadFile(@RequestBody String path) {
        try {
            File file = new File(path + "/chatGPTDto.json");
            s3Uploader.upload(file, "static");
        } catch (Exception e) { return new ResponseEntity(HttpStatus.BAD_REQUEST); }
        return new ResponseEntity(HttpStatus.OK);
    }
}