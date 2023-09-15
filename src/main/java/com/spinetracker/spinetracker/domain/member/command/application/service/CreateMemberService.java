package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberDTO;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateMemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public CreateMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 소셜 로그인 시
    @Transactional
    public Member createMember(CreateMemberDTO createMemberDTO) {
        Member createdMember = new Member(
                createMemberDTO.getEmail(),
                createMemberDTO.getName(),
                createMemberDTO.getUID(),
                createMemberDTO.getProfileImage(),
                RoleEnum.valueOf(createMemberDTO.getRole()),
                createMemberDTO.getPlatformEnum()
        );
        return memberRepository.save(createdMember);
    }
}
