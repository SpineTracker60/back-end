package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.application.dto.UpdateMemberDTO;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateMemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public UpdateMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 소셜 로그인 시
    @Transactional
    public Member updateMember(Long memberId, UpdateMemberDTO updateMemberDTO) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if(findMember.isPresent()) {
            Member member = findMember.get();
            member.setProfileImage(updateMemberDTO.getProfileImage());
            member.setName(updateMemberDTO.getName());

            return member;
        }
        return null;
    }
}
