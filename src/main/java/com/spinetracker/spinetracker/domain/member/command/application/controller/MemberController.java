package com.spinetracker.spinetracker.domain.member.command.application.controller;

import com.spinetracker.spinetracker.domain.member.command.application.service.DeleteMemberService;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.common.response.ResponseDTO;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final DeleteMemberService deleteMemberService;

    @Autowired
    public MemberController(DeleteMemberService deleteMemberService) {
        this.deleteMemberService = deleteMemberService;
    }

    // 회원 탈퇴
    @DeleteMapping("/")
    public ResponseEntity<ResponseDTO> deleteMemberInfo(@CurrentMember UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getId();

        deleteMemberService.delete(memberId);

        return ResponseEntity.noContent().build();
    }
}
