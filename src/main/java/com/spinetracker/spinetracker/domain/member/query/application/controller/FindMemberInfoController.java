package com.spinetracker.spinetracker.domain.member.query.application.controller;

import com.spinetracker.spinetracker.domain.member.query.application.service.FindMemberService;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.common.response.ResponseDTO;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class FindMemberInfoController {

    private final FindMemberService findMemberService;

    @Autowired
    public FindMemberInfoController(FindMemberService findMemberService) {
        this.findMemberService = findMemberService;
    }

    // 회원가입 시 추가 정보 입력 여부 확인 조회
    @GetMapping("info/added")
    public ResponseEntity<ResponseDTO> addMemberInfo(@CurrentMember UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getId();

        return ResponseEntity.ok()
                .body(
                        new ResponseDTO(HttpStatus.OK,
                                "성공!!",
                                findMemberService.isAddedInformation(memberId)
                        )
                );
    }
}
