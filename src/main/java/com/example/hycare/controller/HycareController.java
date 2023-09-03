package com.example.hycare.controller;

import com.example.hycare.dto.HycareDto;
import com.example.hycare.Service.HycareService;
import com.example.hycare.entity.ApiResult;
import com.example.hycare.entity.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hy-care")
public class HycareController {
    private final HycareService hycareService;

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

}
