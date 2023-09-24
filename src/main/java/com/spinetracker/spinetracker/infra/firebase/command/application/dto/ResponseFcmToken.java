package com.spinetracker.spinetracker.infra.firebase.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ResponseFcmToken {

    @Schema(type = "Long", example = "1", description="사용자 번호 입니다.")
    @NotBlank
    @NotNull
    @JsonProperty("member_id")
    private final Long memberId;
    @Schema(type = "String", example = "BKagOny0KF_2pCJQ3m....moL0ewzQ8rZu", description="Firebase에서 발급 받은 토큰 입니다.")
    @NotBlank
    @NotNull
    @JsonProperty("fcm_token")
    private final String FcmToken;

    public ResponseFcmToken(Long memberId, String fcmToken) {
        this.memberId = memberId;
        FcmToken = fcmToken;
    }
}