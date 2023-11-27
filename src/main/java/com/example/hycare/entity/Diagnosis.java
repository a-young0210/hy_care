package com.example.hycare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Diagnosis {

    @Id
    private String diagId;
    private Timestamp diagTime;
    private String diagLink;
    private String doctorName;
    private String patientName;
    private String consultationSheet;
}
