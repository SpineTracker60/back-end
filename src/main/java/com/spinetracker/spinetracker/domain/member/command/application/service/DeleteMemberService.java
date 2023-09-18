package com.spinetracker.spinetracker.domain.member.command.application.service;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.Member;
import com.spinetracker.spinetracker.domain.member.command.domain.repository.MemberRepository;
import com.spinetracker.spinetracker.domain.member.command.domain.service.RequestUnlinkMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DeleteMemberService {

    private MemberRepository memberRepository;

    private RequestUnlinkMember requestUnlinkMember;

    @Autowired
    public DeleteMemberService(MemberRepository memberRepository, RequestUnlinkMember requestUnlinkMember) {
        this.memberRepository = memberRepository;
        this.requestUnlinkMember = requestUnlinkMember;
    }

    @Transactional
    public boolean delete(Long memberId) {

        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            Member findMember = member.get();
            boolean isSocialLoginUnlinked = requestUnlinkMember.unlinkSocial(findMember);
            memberRepository.delete(findMember);
            return isSocialLoginUnlinked;
        }
        return false;
    }
}
