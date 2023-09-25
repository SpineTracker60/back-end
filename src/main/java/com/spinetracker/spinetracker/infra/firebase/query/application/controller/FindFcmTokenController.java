package com.spinetracker.spinetracker.infra.firebase.query.application.controller;

import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import com.spinetracker.spinetracker.infra.firebase.command.application.dto.ResponseFcmToken;
import com.spinetracker.spinetracker.infra.firebase.query.application.dto.FindFcmTokenDTO;
import com.spinetracker.spinetracker.infra.firebase.query.application.service.FindFcmTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name="FCM TOKEN", description = "FCM 토큰 관련 API")
@RestController
@RequestMapping("/fcm")
public class FindFcmTokenController {

    private final FindFcmTokenService findFcmTokenService;

    @Autowired
    public FindFcmTokenController(FindFcmTokenService findFcmTokenService) {
        this.findFcmTokenService = findFcmTokenService;
    }

    @Operation(
            summary = "FCM 토큰 조회",
            description = "사용자의 토큰을 기반으로 사용자의 웹 푸시용 토큰을 조회합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseFcmToken.class))}),
            //@ApiResponse(code = 204, message = "member not exists"),
    })
    @GetMapping
    public ResponseEntity<ResponseFcmToken> create(@CurrentMember UserPrincipal userPrincipal) {
        Long memberId = userPrincipal.getId();

        FindFcmTokenDTO findFcmTokenDTO = findFcmTokenService.findByMemberId(memberId);

        return ResponseEntity.ok().body(new ResponseFcmToken(memberId, findFcmTokenDTO.getFcmToken()));
    }
}
