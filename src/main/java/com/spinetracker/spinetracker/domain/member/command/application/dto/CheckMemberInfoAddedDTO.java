package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckMemberInfoAddedDTO {
    @Schema(type = "boolean", example = "true", description = "사용자 정보 추가 여부 입니다.")
    @JsonProperty("is_added")
    private boolean isAdded;

    public CheckMemberInfoAddedDTO() {}

    public CheckMemberInfoAddedDTO(boolean isAdded) {
        this.isAdded = isAdded;
    }
}
