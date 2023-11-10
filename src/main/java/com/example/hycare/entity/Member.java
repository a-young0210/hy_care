package com.example.hycare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int memId;
    private String memName;
    private Timestamp editTime;
    private String isDctor;
    private String email;

    private String diagId;
}
