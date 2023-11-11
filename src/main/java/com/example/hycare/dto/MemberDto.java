package com.example.hycare.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private int memId;
    private String memName;
    private Timestamp editTime;
    private String isDctor;
    private String email;

    private String diagId;
}
