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
    public Member createMember(CreateMemberDTO createMemberBySocialDTO) {
        Member createdMemberBySocial = new Member(
                createMemberBySocialDTO.getEmail(),
                createMemberBySocialDTO.getName(),
                createMemberBySocialDTO.getUID(),
                createMemberBySocialDTO.getProfileImage(),
                RoleEnum.valueOf(createMemberBySocialDTO.getRole()),
                createMemberBySocialDTO.getPlatformEnum()
        );
        return memberRepository.save(createdMemberBySocial);
    }
}
