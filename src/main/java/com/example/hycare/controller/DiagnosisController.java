package com.example.hycare.controller;

import com.example.hycare.dto.DiagnosisDto;
import com.example.hycare.Service.DiagnosisService;
import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @Value("${server.host.api}")
    private String baseUrl;

    @PostMapping("/save/{uuid}")
    public ResponseEntity<ResultEntity> saveDiagnosis (@RequestBody DiagnosisDto diagnosisDto, @PathVariable String uuid) {
        try {
            diagnosisService.saveDiagnosis(diagnosisDto, uuid);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{uuid}")
    public ResultEntity<DiagnosisDto> findById (@PathVariable("uuid") String uuid) {
        try {
            DiagnosisDto diagnosisDto = diagnosisService.findData(uuid);
            return new ResultEntity<>(diagnosisDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/find-diagText/{uuid}")
    public ResultEntity<Object> findDiagText (@PathVariable("uuid") String uuid) {
        try {
            // diagnosis DB에서 데이터 가져오기
            DiagnosisDto diagnosisDto = diagnosisService.findData(uuid);

            // s3에서 가져올 파일 이름 구하기
            String[] diagUrl = diagnosisDto.getDiagLink().split("/");
            String[] s3find = diagUrl[4].split("_");

            // s3 조회 api 호출
            String url = baseUrl + "/s3-find/" + s3find[0];
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity httpEntity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ResultEntity> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    httpEntity,
                    ResultEntity.class);

            return new ResultEntity<>(response.getBody().getData());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
