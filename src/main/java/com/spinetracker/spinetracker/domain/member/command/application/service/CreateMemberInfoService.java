package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.MemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.MemberInfo;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.GenderEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.MemberVO;
import com.spinetracker.spinetracker.domain.member.command.domain.repository.MemberInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateMemberInfoService {

    private final MemberInfoRepository memberInfoRepository;

    @Autowired
    public CreateMemberInfoService(MemberInfoRepository memberInfoRepository) {
        this.memberInfoRepository = memberInfoRepository;
    }

    @Transactional
    public MemberInfo createMemberInfo(MemberInfoDTO memberInfoDTO, Long memberId) {

        MemberVO memberVO = new MemberVO(memberId);
        MemberInfo createdMemberInfo = new MemberInfo(
                GenderEnum.valueOf(memberInfoDTO.getGender()),
                memberInfoDTO.getBirthdate(),
                memberInfoDTO.getJob(),
                memberVO
        );
        return memberInfoRepository.save(createdMemberInfo);
    }
}
