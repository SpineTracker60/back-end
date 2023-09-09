package com.spinetracker.spinetracker.domain.posture.command.application.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@ToString
public class CreatePostureDTO {
    private final Long memberId;
    private final String postureTag;
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public CreatePostureDTO(Long memberId, String postureTag, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.memberId = memberId;
        this.postureTag = postureTag;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
