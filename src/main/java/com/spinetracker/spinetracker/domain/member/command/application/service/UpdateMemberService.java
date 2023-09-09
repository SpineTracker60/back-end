package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.UpdateMemberByLocalDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.UpdateMemberBySocialDTO;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateMemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public UpdateMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 자체 로그인 시
    public boolean updateMemberByLocal(Long memberId, UpdateMemberByLocalDTO updateMemberByLocalDTO) {
        Optional<Member> findMemberByLocal = memberRepository.findById(memberId);
        if (findMemberByLocal.isPresent()) {
            Member updateMemberByLocal = findMemberByLocal.get();
            if (updateMemberByLocalDTO.getPassword() != null) {
                updateMemberByLocal.setPassword(updateMemberByLocalDTO.getPassword());
            }
            if (updateMemberByLocalDTO.getRole() != null) {
                updateMemberByLocal.setRole(updateMemberByLocalDTO.getRole());
            }
            if (updateMemberByLocalDTO.getMemberInfo() != null) {
                updateMemberByLocal.setMemberInfo(updateMemberByLocalDTO.getMemberInfo());
            }

            return true;
        } else {
            return false;
        }
    }

    // 소셜 로그인 시
    public boolean updateMemberBySocial(Long memberId, UpdateMemberBySocialDTO updateMemberBySocialDTO) {
        Optional<Member> findMemberBySocial = memberRepository.findById(memberId);
        if (findMemberBySocial.isPresent()) {
            Member updateMemberBySocial = findMemberBySocial.get();
            if (updateMemberBySocialDTO.getProfileImage() != null) {
                updateMemberBySocial.setProfileImage(updateMemberBySocialDTO.getProfileImage());
            }
            if (updateMemberBySocialDTO.getRole() != null) {
                updateMemberBySocial.setRole(updateMemberBySocialDTO.getRole());
            }
            if (updateMemberBySocialDTO.getMemberInfo() != null) {
                updateMemberBySocial.setMemberInfo(updateMemberBySocialDTO.getMemberInfo());
            }
            return true;
        } else {
            return false;
        }
    }
}
