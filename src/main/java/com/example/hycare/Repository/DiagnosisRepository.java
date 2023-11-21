package com.example.hycare.Repository;

import com.example.hycare.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends  JpaRepository<Diagnosis,String>{
}
