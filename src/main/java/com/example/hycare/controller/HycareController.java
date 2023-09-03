package com.example.hycare.controller;

import com.example.hycare.chatGPT.ChatGPTDto;
import com.example.hycare.dto.HycareDto;
import com.example.hycare.Service.HycareService;
import com.example.hycare.entity.ApiResult;
import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hy-care")
public class HycareController {
    private final HycareService hycareService;

    @Value("${server.host.api}")
    private String baseUrl;

    @PostMapping("/save")
    public ResponseEntity<ResultEntity> saveHycare (@RequestBody HycareDto hycareDto) {
        try {
            hycareService.saveHycare(hycareDto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResultEntity<HycareDto> findById (@PathVariable("id") Long id) {
        try {
            HycareDto hycareDto = hycareService.findData(id);
            return new ResultEntity<>(hycareDto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/find-diagText/{id}")
    public ResultEntity<Object> findDiagText (@PathVariable("id") Long id) {
        try {
            HycareDto hycareDto = hycareService.findData(id);

            String[] diagUrl = hycareDto.getDiagText().split("/");

            String[] s3find = diagUrl[4].split("_");
            Long diagId =  Long.parseLong(s3find[0]);


            // s3 조회 api 호출
            String url = baseUrl + "/s3-find/" + diagId;
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
