package com.spinetracker.spinetracker.infra.firebase.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CreateFcmTokenDTO {

    @Schema(type = "String", example = "BKagOny0KF_2pCJQ3m....moL0ewzQ8rZu", description="Firebase에서 발급 받은 토큰 입니다.")
    @NotBlank
    @NotNull
    @JsonProperty("fcm_token")
    private String token;

    public CreateFcmTokenDTO() {}

    public CreateFcmTokenDTO(String token) {
        this.token = token;
    }
}
