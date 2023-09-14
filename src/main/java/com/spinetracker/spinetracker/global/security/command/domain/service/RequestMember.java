package com.spinetracker.spinetracker.global.security.command.domain.service;

import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;

public interface RequestMember {

    FindMemberDTO getMemberById(long memberId);
}
