package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberByLocalDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberBySocialDTO;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateMemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public CreateMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 자체 로그인 시
    public Member createMemberByLocal(CreateMemberByLocalDTO createMemberByLocalDTO) {
        Member createdMemberByLocal = new Member(
                createMemberByLocalDTO.getEmail(),
                createMemberByLocalDTO.getPassword(),
                createMemberByLocalDTO.getRole(),
                createMemberByLocalDTO.getMemberInfo()
        );
        return memberRepository.save(createdMemberByLocal);
    }
    // 소셜 로그인 시
    public Member createMemberBySocial(CreateMemberBySocialDTO createMemberBySocialDTO) {
        Member createdMemberBySocial = new Member(
                createMemberBySocialDTO.getEmail(),
                createMemberBySocialDTO.getUID(),
                createMemberBySocialDTO.getProfileImage(),
                createMemberBySocialDTO.getRole(),
                createMemberBySocialDTO.getPlatformEnum(),
                createMemberBySocialDTO.getMemberInfo()
        );
        return memberRepository.save(createdMemberBySocial);
    }
}
