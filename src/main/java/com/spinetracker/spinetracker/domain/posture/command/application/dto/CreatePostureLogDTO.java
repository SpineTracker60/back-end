package com.spinetracker.spinetracker.domain.posture.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CreatePostureLogDTO {

    @JsonProperty("posture_tag")
    private String postureTag;
    private LocalDate date;
    @JsonProperty("start_time")
    private LocalTime startTime;
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
