package com.example.hycare.Service;

import com.example.hycare.Repository.MemberRepository;
import com.example.hycare.dto.MemberDto;
import com.example.hycare.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void saveMember(MemberDto memberDto) {
        // Dto -> Entity
        Member member = new Member();
        member.setMemName(memberDto.getMemName());
        member.setEmail(memberDto.getEmail());
        // 의사 : 0, 환자 : 1
        member.setIsDoctor(memberDto.getIsDoctor().equals("0") ? "D" : "P");
        memberRepository.save(member);

    }

    public MemberDto findByEmail(String email) {
        MemberDto memberDto = new MemberDto();
        Member member = memberRepository.findByEmail(email);
        List<String> diagId = new ArrayList<>();
        if(member != null) {
            if(member.getEmail() != null || !member.getEmail().isEmpty()) {
                if(member.getDiagId() != null) {
                    diagId = List.of(member.getDiagId().split(","));
                }
                memberDto = MemberDto.builder()
                        .memId(member.getMemId())
                        .memName(member.getMemName())
                        .editTime(member.getEditTime())
                        .isDoctor(member.getIsDoctor())
                        .email(member.getEmail())
                        .diagId(diagId)
                        .build();
            }
        }
        return memberDto;
    }

    public MemberDto findById(int id) {
        MemberDto memberDto = null;
        List<String> diagId = new ArrayList<>();
        Optional<Member> member = memberRepository.findById((long) id);
        if (!member.isEmpty() || member != null) {
            if(member.get().getDiagId() != null) {
                diagId = List.of(member.get().getDiagId().split(","));
            }
            memberDto = MemberDto.builder()
                    .memId(member.get().getMemId())
                    .memName(member.get().getMemName())
                    .editTime(member.get().getEditTime())
                    .isDoctor(member.get().getIsDoctor())
                    .email(member.get().getEmail())
                    .diagId(diagId)
                    .build();
        }
        return memberDto;
    }

    public MemberDto addDiagId(MemberDto memberDto) {
        // Dto -> Entity
        Member member = new Member();
        member.setMemId(memberDto.getMemId());
        member.setMemName(memberDto.getMemName());
        member.setEditTime(java.sql.Timestamp.valueOf(LocalDateTime.now()));    // 수정된 시간
        member.setIsDoctor(memberDto.getIsDoctor());
        member.setEmail(memberDto.getEmail());
        member.setDiagId(String.join(",", memberDto.getDiagId()));

        memberRepository.save(member);
        return memberDto;
    }
}
