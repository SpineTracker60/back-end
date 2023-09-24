package com.spinetracker.spinetracker.infra.firebase.command.application.controller;

import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import com.spinetracker.spinetracker.infra.firebase.command.application.dto.CreateFcmTokenDTO;
import com.spinetracker.spinetracker.infra.firebase.command.application.dto.ResponseFcmToken;
import com.spinetracker.spinetracker.infra.firebase.command.application.service.CreateFcmTokenService;
import com.spinetracker.spinetracker.infra.firebase.command.domain.entity.FCMToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Tag(name="FCM TOKEN", description = "FCM 토큰 관련 API")
@RestController
@RequestMapping("/fcm")
public class CreateFcmTokenController {

    private final CreateFcmTokenService createFcmTokenService;

    @Autowired
    public CreateFcmTokenController(CreateFcmTokenService createFcmTokenService) {
        this.createFcmTokenService = createFcmTokenService;
    }

    @Operation(
            summary = "FCM 토큰 생성",
            description = "사용자의 토큰을 기반으로 사용자의 웹 푸시용 토큰을 저장합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseFcmToken.class))}),
            //@ApiResponse(code = 204, message = "member not exists"),
    })
    @PostMapping
    public ResponseEntity<ResponseFcmToken> create(@CurrentMember UserPrincipal userPrincipal, @RequestBody CreateFcmTokenDTO createFcmTokenDTO) {
        Long memberId = userPrincipal.getId();
        String FcmToken = createFcmTokenDTO.getToken();

        FCMToken createdFcmToken = createFcmTokenService.create(memberId, FcmToken);

        return ResponseEntity.created(URI.create("/fcm")).body(
                new ResponseFcmToken(memberId, createdFcmToken.getToken())
        );
    }
}
