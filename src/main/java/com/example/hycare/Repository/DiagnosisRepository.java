package com.example.hycare.Repository;

import com.example.hycare.entity.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends  JpaRepository<Diagnosis,String>{
}
