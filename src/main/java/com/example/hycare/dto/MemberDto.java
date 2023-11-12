package com.example.hycare.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private int memId;
    private String memName;
    private Timestamp editTime;
    private String isDoctor;
    private String email;

    private List<String> diagId;
}
