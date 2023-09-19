package com.spinetracker.spinetracker.domain.posture.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CreatePostureLogDTO {

    @Schema(type = "String", example = "TEXTNECK", description="자세 태그 입니다.", allowableValues={"TEXTNECK", "ASYMMETRY", "STOOPED", "SLEEPINESS", "START", "END"})
    @NotBlank
    @NotNull
    @JsonProperty("posture_tag")
    private String postureTag;

    @Schema(type = "String", example = "2023-09-18", description="한 자세 태그의 생성 일자 입니다." )
    @NotBlank
    @NotNull
    private LocalDate date;

    @Schema(type = "String", example = "12:00:00", description="한 자세 태그의 시작 시간 입니다." )
    @NotBlank
    @NotNull
    @JsonProperty("start_time")
    private LocalTime startTime;

    @Schema(type = "String", example = "12:00:00", description="한 자세 태그의 종료 시간 입니다." )
    @NotBlank
    @NotNull
    @JsonProperty("end_time")
    private LocalTime endTime;

    public CreatePostureLogDTO() {}

    public CreatePostureLogDTO(String postureTag, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.postureTag = postureTag;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
