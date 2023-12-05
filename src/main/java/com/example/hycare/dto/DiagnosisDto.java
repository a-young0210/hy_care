package com.example.hycare.dto;

import lombok.*;
import org.springframework.http.HttpStatusCode;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosisDto {
    private String diagId;
    private Timestamp diagTime;
    private String diagLink;
    private String doctorName;
    private String patientName;
    private String consultationSheet;
}
