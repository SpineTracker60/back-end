package com.spinetracker.spinetracker.domain.posture.query.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;


public class UnStableRatioDTO {

    @JsonProperty("turtle_neck")
    @Schema(type = "Double", example = "0.21", description="주간 불안정 자세 중 거북목 자세의 비율 입니다.")
    private final Double textNeck;
    @JsonProperty("asymmetry")
    @Schema(type = "Double", example = "0.16", description="주간 불안정 자세 중 비대칭 자세의 비율 입니다.")
    private final Double asymmetry;
    @JsonProperty("stooped_position")
    @Schema(type = "Double", example = "0.03", description="주간 불안정 자세 중 구부정 자세의 비율 입니다.")
    private final Double stooped;
    @JsonProperty("sleepiness")
    @Schema(type = "Double", example = "0.6", description="주간 불안정 자세 중 졸음 자세의 비율 입니다.")
    private final Double sleepiness;

    public UnStableRatioDTO(Double textNeck, Double asymmetry, Double stooped, Double sleepiness) {
        this.textNeck = textNeck;
        this.asymmetry = asymmetry;
        this.stooped = stooped;
        this.sleepiness = sleepiness;
    }
}
