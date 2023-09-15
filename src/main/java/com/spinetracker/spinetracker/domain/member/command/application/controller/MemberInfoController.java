package com.spinetracker.spinetracker.domain.member.command.application.controller;

import com.spinetracker.spinetracker.domain.member.command.application.dto.MemberInfoDTO;
import com.spinetracker.spinetracker.domain.member.command.application.service.CreateMemberInfoService;
import com.spinetracker.spinetracker.domain.member.command.application.service.UpdateMemberInfoService;
import com.spinetracker.spinetracker.global.common.response.ResponseDTO;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    // 회원가입시 사용자 정보 추가
    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createMemberInfo(@RequestBody MemberInfoDTO memberInfoDTO, @CurrentMember UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getId();

        if (memberInfoDTO.getGender() == null || memberInfoDTO.getBirthdate() == null || memberInfoDTO.getJob() == null) {
            throw new RuntimeException("정보를 모두 입력해야 합니다.");
        }

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.CREATED, "추가 성공!!", createMemberInfoService.createMemberInfo(memberInfoDTO,memberId)));
    }

    // 마이페이지에서 사용자 정보 수정
    @PutMapping("/")
    public ResponseEntity<ResponseDTO> updateMemberInfo(@RequestBody MemberInfoDTO memberInfoDTO, @CurrentMember UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getId();

        if (memberInfoDTO.getGender() == null || memberInfoDTO.getBirthdate() == null || memberInfoDTO.getJob() == null) {
            throw new RuntimeException("정보를 모두 입력해야 합니다.");
        }

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "변경 성공!!", updateMemberInfoService.updateMemberInfo(memberInfoDTO,memberId)));
    }



    // 회원 탈퇴
//    @DeleteMapping("/")

}
