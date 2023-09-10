package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberByLocalDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberBySocialDTO;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.AgeRangeEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.GenderEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.MemberInfoVO;
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

    // 자체 로그인 시
    @Transactional
    public Member createMemberByLocal(CreateMemberByLocalDTO createMemberByLocalDTO) {

        GenderEnum memberGender = GenderEnum.valueOf(createMemberByLocalDTO.getGender());
        AgeRangeEnum memberAgeRange = AgeRangeEnum.valueOf(createMemberByLocalDTO.getAgeRange());
        MemberInfoVO newMemberInfoVO = new MemberInfoVO(
                createMemberByLocalDTO.getName(),
                memberGender,
                memberAgeRange,
                createMemberByLocalDTO.getJob()
        );

        RoleEnum memberRole = RoleEnum.valueOf(createMemberByLocalDTO.getRole());
        Member createdMemberByLocal = new Member(
                createMemberByLocalDTO.getEmail(),
                createMemberByLocalDTO.getPassword(),
                memberRole,
                newMemberInfoVO
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
