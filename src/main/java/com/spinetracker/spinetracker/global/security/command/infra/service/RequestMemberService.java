package com.spinetracker.spinetracker.global.security.command.infra.service;

import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import com.spinetracker.spinetracker.domain.member.query.application.service.FindMemberService;
import com.spinetracker.spinetracker.global.common.annotation.InfraService;
import com.spinetracker.spinetracker.global.security.command.domain.service.RequestMember;
import org.springframework.beans.factory.annotation.Autowired;

@InfraService
public class RequestMemberService implements RequestMember {

    private final FindMemberService findMemberService;

    @Autowired
    public RequestMemberService(FindMemberService findMemberService) {
        this.findMemberService = findMemberService;
    }
    @Override
    public FindMemberDTO getMemberById(long memberId) {
        return findMemberService.findById(memberId);
    }
}
