package com.spinetracker.spinetracker.domain.member.command.application.controller;

import com.spinetracker.spinetracker.domain.member.command.application.dto.CheckMemberInfoAddedDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.CreateMemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.command.application.dto.FindMemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.command.application.service.CreateMemberInfoService;
import com.spinetracker.spinetracker.domain.member.command.application.service.UpdateMemberInfoService;
import com.spinetracker.spinetracker.global.common.response.ResponseDTO;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
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
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@Tag(name = "MemberInfo", description = "추가 정보 관련 API")
@RestController
@RequestMapping("/member/info")
public class MemberInfoController {

    private final CreateMemberInfoService createMemberInfoService;
    private final UpdateMemberInfoService updateMemberInfoService;
    @Autowired
    public MemberInfoController(CreateMemberInfoService createMemberInfoService, UpdateMemberInfoService updateMemberInfoService) {
        this.createMemberInfoService = createMemberInfoService;
        this.updateMemberInfoService = updateMemberInfoService;
    }

    // 회원가입 완료 후 추가 정보 입력
    @Operation(
            summary = "회원가입 완료 후 추가 정보 입력",
            description = "회원가입 시 추가 정보를 입력합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreateMemberInfoDTO.class))}),
    })
    @PostMapping
    public ResponseEntity<ResponseDTO> createMemberInfo(@RequestBody CreateMemberInfoDTO memberInfoDTO, @CurrentMember UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getId();

        if (memberInfoDTO.getGender() == null || memberInfoDTO.getBirthdate() == null || memberInfoDTO.getJob() == null) {
            throw new RuntimeException("정보를 모두 입력해야 합니다.");
        }

        return ResponseEntity.created(URI.create("/member/info"))
                .body(new ResponseDTO(HttpStatus.CREATED,
                        "추가 성공!!", createMemberInfoService.createMemberInfo(memberInfoDTO,memberId))
                );
    }

    // 마이페이지에서 사용자 정보 수정
    @PutMapping
    public ResponseEntity<ResponseDTO> updateMemberInfo(@RequestBody FindMemberInfoDTO memberInfoDTO, @CurrentMember UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getId();

        if (memberInfoDTO.getGender() == null || memberInfoDTO.getBirthdate() == null || memberInfoDTO.getJob() == null) {
            throw new RuntimeException("정보를 모두 입력해야 합니다.");
        }

        return ResponseEntity.ok()
                .body(new ResponseDTO(HttpStatus.OK,
                        "변경 성공!!",
                        updateMemberInfoService.updateMemberInfo(memberInfoDTO,memberId))
                );
    }
}
