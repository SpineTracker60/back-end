package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.UpdateMemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.MemberInfo;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.GenderEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.AgeRangeVO;
import com.spinetracker.spinetracker.domain.member.command.domain.repository.MemberInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateMemberInfoService {

    private final MemberInfoRepository memberInfoRepository;

    @Autowired
    public UpdateMemberInfoService(MemberInfoRepository memberInfoRepository) {
        this.memberInfoRepository = memberInfoRepository;
    }

    @Transactional
    public MemberInfo updateMemberInfo(UpdateMemberInfoDTO memberInfoDTO, Long memberId) {

        Optional<MemberInfo> findMemberInfo = memberInfoRepository.findMemberInfoByMemberVO_MemberId(memberId);
        if(findMemberInfo.isPresent()) {
            MemberInfo memberInfo = findMemberInfo.get();

            AgeRangeVO ageRangeVO = new AgeRangeVO(memberInfoDTO.getBirthdate()); // 생년월일을 바탕으로 나이대를 계산만 함
            memberInfo.setGender(GenderEnum.valueOf(memberInfoDTO.getGender()));    // 성별
            memberInfo.setBirthDate(memberInfoDTO.getBirthdate());  // 생년월일을 저장 (업데이트)
            memberInfo.setJob(memberInfoDTO.getJob());  // 직업

            // 체인 방식으로 사용 가능
//            memberInfo.setGender(GenderEnum.valueOf(memberInfoDTO.getGender())) // 성별
//                    .setBirthDate(memberInfoDTO.getBirthdate()) // 생년월일을 저장 (업데이트)
//                    .setJob(memberInfoDTO.getJob());

            return memberInfo;
        }
        return null;
    }
}
