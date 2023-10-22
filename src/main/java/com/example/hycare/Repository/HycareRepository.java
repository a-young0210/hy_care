package com.example.hycare.Repository;

import com.example.hycare.entity.Hycare;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HycareRepository extends  JpaRepository<Hycare,Long>{


}
