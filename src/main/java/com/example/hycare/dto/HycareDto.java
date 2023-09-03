package com.example.hycare.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HycareDto {
    private Long id;
    private String diagText;
    private String sheetImg;
    private Timestamp sttTime;
    private Timestamp EndTime;
}
