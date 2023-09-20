package com.spinetracker.spinetracker.domain.member.query.application.controller;

import com.spinetracker.spinetracker.domain.member.command.application.dto.MemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.query.application.service.FindMemberService;
import com.spinetracker.spinetracker.domain.posture.command.application.dto.ResponsePosture;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.common.response.ResponseDTO;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MemberInfo", description = "추가 정보 관련 API")
@RestController
@RequestMapping("/member")
public class FindMemberInfoController {

    private final FindMemberService findMemberService;

    @Autowired
    public FindMemberInfoController(FindMemberService findMemberService) {
        this.findMemberService = findMemberService;
    }

    // 회원가입 시 추가 정보 입력 여부 확인 조회
    @Operation(
            summary = "추가 정보 입력 여부 확인",
            description = "로그인 시 추가 정보가 입력이 되어있는 회원인지 확인합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MemberInfoDTO.class))}),
    })
    @GetMapping("/info/added")
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
