package com.spinetracker.spinetracker.domain.member.query.application.controller;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CheckMemberInfoAddedDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.FindMemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import com.spinetracker.spinetracker.domain.member.query.application.service.FindMemberService;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "MemberInfo", description = "유저 정보 관련 API")
@RestController
@RequestMapping("/member")
public class FindMemberInfoController {

    private final FindMemberService findMemberService;

    @Autowired
    public FindMemberInfoController(FindMemberService findMemberService) {
        this.findMemberService = findMemberService;
    }

    @Operation(
            summary = "추가 정보 입력 여부 확인",
            description = "로그인 시 추가 정보가 입력이 되어있는 회원인지 확인합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CheckMemberInfoAddedDTO.class))}),
    })
    @GetMapping("/info/added")
    public ResponseEntity<CheckMemberInfoAddedDTO> addMemberInfo(@CurrentMember UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getId();

        return ResponseEntity.ok()
                .body(new CheckMemberInfoAddedDTO(
                        findMemberService.isAddedInformation(memberId))
                );
    }

    @Operation(
            summary = "유저 정보 조회",
            description = "현재 로그인 되어 있는 회원의 정보를 확인합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FindMemberDTO.class))}),
    })
    @GetMapping
    public ResponseEntity<FindMemberDTO> getMember(@CurrentMember UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getId();

        FindMemberDTO findMember = findMemberService.findById(memberId);

        return ResponseEntity.ok()
                .body(new FindMemberDTO(
                        findMember.getId(),
                        findMember.getName(),
                        findMember.getProfileImage(),
                        findMember.getRole()));
    }

    @Operation(
            summary = "유저 추가 정보 조회",
            description = "회원의 추가 정보를 확인합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FindMemberInfoDTO.class))}),
    })
    @GetMapping("/info/query")
    public ResponseEntity<FindMemberInfoDTO> getMemberInfo(@RequestParam Long memberId) {

        FindMemberInfoDTO findMemberInfo = findMemberService.findInfoById(memberId);

        return ResponseEntity.ok()
                .body(findMemberInfo);
    }
}
