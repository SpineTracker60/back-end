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
import java.util.Optional;

@Service
public class CreateMemberInfoService {

    private final MemberInfoRepository memberInfoRepository;

    @Autowired
    public CreateMemberInfoService(MemberInfoRepository memberInfoRepository) {
        this.memberInfoRepository = memberInfoRepository;
    }

    @Transactional
    public MemberInfo createMemberInfo(CreateMemberInfoDTO createMemberInfoDTO, Long memberId) {

        // 성별, 생년월일, 직업 처음에 다 null임
        MemberVO memberVO = new MemberVO(memberId);
        GenderEnum inputGender = null;
        LocalDate inputBirthDate = null;
        String inputJob = null;

        // null이 아니면 dto에 저장되어있는 성별, 생년월일, 직업을 가지고 옴
        if (createMemberInfoDTO.getGender() != null ) {
            inputGender = GenderEnum.valueOf(createMemberInfoDTO.getGender());
        } if(createMemberInfoDTO.getBirthdate() != null) {
            inputBirthDate = createMemberInfoDTO.getBirthdate();
        } if (createMemberInfoDTO.getJob() != null) {
            inputJob = createMemberInfoDTO.getJob();
        }

        // memberId를 통해 먼저 사용자 추가 정보를 찾음
        Optional<MemberInfo> findMemberInfo = memberInfoRepository.findMemberInfoByMemberVO_MemberId(memberId);
        // 사용자 추가정보가 비어있으면
        if(findMemberInfo.isEmpty()) {
            // 추가정보를 생성해줌
            MemberInfo createdMemberInfo = new MemberInfo(inputGender, inputBirthDate, inputJob, memberVO);
            // 저장
            return memberInfoRepository.save(createdMemberInfo);
            // 사용자 추가정보가 있으면
        } else {
            // 사용자 정보를 가져오고
           MemberInfo memberInfo = findMemberInfo.get();
           // 사용자 정보를 수정해줌
           memberInfo.setBirthDate(inputBirthDate);
           memberInfo.setJob(inputJob);
           memberInfo.setGender(inputGender);
           // 저장
           return memberInfoRepository.save(memberInfo);
        }
    }
}
