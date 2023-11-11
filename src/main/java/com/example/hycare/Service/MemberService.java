package com.example.hycare.Service;

import com.example.hycare.Repository.MemberRepository;
import com.example.hycare.dto.MemberDto;
import com.example.hycare.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void saveMember(MemberDto memberDto) {
        // Dto -> Entity
        Member member = new Member();

        memberRepository.save(member);
    }
}
