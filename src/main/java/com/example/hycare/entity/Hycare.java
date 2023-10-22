package com.example.hycare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Hycare {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String diagText;
    private String sheetImg;
    private Timestamp sttTime;
    private Timestamp EndTime;

}
