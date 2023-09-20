package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.FindMemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.MemberInfo;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.GenderEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.MemberVO;
import com.spinetracker.spinetracker.domain.member.command.domain.repository.MemberInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class CreateMemberInfoService {

    private final MemberInfoRepository memberInfoRepository;

    @Autowired
    public CreateMemberInfoService(MemberInfoRepository memberInfoRepository) {
        this.memberInfoRepository = memberInfoRepository;
    }

    @Transactional
    public MemberInfo createMemberInfo(CreateMemberInfoDTO createMemberInfoDTO, Long memberId) {

        MemberVO memberVO = new MemberVO(memberId);
        GenderEnum inputGender = null;
        LocalDate inputBirthDate = null;
        String inputJob = null;

        if (createMemberInfoDTO.getGender() != null ) {
            inputGender = GenderEnum.valueOf(createMemberInfoDTO.getGender());
        } if(createMemberInfoDTO.getBirthdate() != null) {
            inputBirthDate = createMemberInfoDTO.getBirthdate();
        } if (createMemberInfoDTO.getJob() != null) {
            inputJob = createMemberInfoDTO.getJob();
        }

        MemberInfo createdMemberInfo = new MemberInfo(inputGender, inputBirthDate, inputJob, memberVO);
        return memberInfoRepository.save(createdMemberInfo);
    }
}
