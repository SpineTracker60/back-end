package com.spinetracker.spinetracker.infra.firebase.command.application.controller;

import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import com.spinetracker.spinetracker.infra.firebase.command.application.dto.CreateFcmTokenDTO;
import com.spinetracker.spinetracker.infra.firebase.command.application.dto.ResponseFcmToken;
import com.spinetracker.spinetracker.infra.firebase.command.application.service.UpdateFcmTokenService;
import com.spinetracker.spinetracker.infra.firebase.command.domain.entity.FCMToken;
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
public class UpdateFcmTokenController {

    private final UpdateFcmTokenService updateFcmTokenService;

    @Autowired
    public UpdateFcmTokenController(UpdateFcmTokenService updateFcmTokenService) {
        this.updateFcmTokenService = updateFcmTokenService;
    }

    @Operation(
            summary = "FCM 토큰 업데이트",
            description = "사용자의 토큰을 기반으로 사용자의 웹 푸시용 토큰을 업데이트합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponseFcmToken.class))}),
            //@ApiResponse(code = 204, message = "member not exists"),
    })

    @PutMapping
    public ResponseEntity<ResponseFcmToken> update(@CurrentMember UserPrincipal userPrincipal, @RequestBody CreateFcmTokenDTO createFcmTokenDTO) {
        Long memberId = userPrincipal.getId();
        String FcmToken = createFcmTokenDTO.getToken();

        FCMToken updatededFcmToken = updateFcmTokenService.update(memberId, FcmToken);

        return ResponseEntity.ok().body(
                new ResponseFcmToken(memberId, updatededFcmToken.getToken())
        );
    }
}
