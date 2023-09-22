package com.spinetracker.spinetracker.domain.member.command.application.controller;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CheckMemberInfoAddedDTO;
import com.spinetracker.spinetracker.domain.member.command.application.service.DeleteMemberService;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Member", description = "유저 관련 API")
@RestController
@RequestMapping("/member")
public class MemberController {

    private final DeleteMemberService deleteMemberService;

    @Autowired
    public MemberController(DeleteMemberService deleteMemberService) {
        this.deleteMemberService = deleteMemberService;
    }

    @Operation(
            summary = "유저 회원 탈퇴",
            description = "소셜 중 카카오 회원 탈퇴 입니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "noContent", content = { @Content(mediaType = "application/json")}),
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteMemberInfo(@CurrentMember UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getId();

        deleteMemberService.delete(memberId);

        return ResponseEntity.noContent().build();
    }
}
