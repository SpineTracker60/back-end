package com.spinetracker.spinetracker.domain.posture.query.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class FindPostureLogDTO {
    private Long id;
    private String postureTag;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    protected FindPostureLogDTO() {}

    public FindPostureLogDTO(Long id, String postureTag, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.id = id;
        this.postureTag = postureTag;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "FindPostureLogDTO{" +
                "id=" + id +
                ", postureTag='" + postureTag + '\'' +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
