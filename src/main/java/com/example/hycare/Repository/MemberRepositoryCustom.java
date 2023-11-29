package com.example.hycare.Repository;

import com.example.hycare.entity.Member;

public interface MemberRepositoryCustom {
    public Member findByEmail(String memName, String isDoctor);
}
