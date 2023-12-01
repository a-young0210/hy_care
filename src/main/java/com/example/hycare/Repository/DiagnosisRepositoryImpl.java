package com.example.hycare.Repository;

import com.example.hycare.entity.Diagnosis;
import com.example.hycare.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DiagnosisRepositoryImpl implements DiagnosisRepositoryCustom{
    private final EntityManager em;

    public Diagnosis findDiagnosis() {
        Diagnosis diagnosis = null;
        try {
            TypedQuery<Diagnosis> query = em.createQuery("SELECT d from Diagnosis d where d.diagTime IS NULL ", Diagnosis.class);
            List<Diagnosis> results = query.getResultList();

            if (!results.isEmpty()) {
                diagnosis = results.get(0);
            }
        } catch (NoResultException e) {
            // 데이터가 없을 때
            diagnosis = null;
        }
        return diagnosis;
    }
}
