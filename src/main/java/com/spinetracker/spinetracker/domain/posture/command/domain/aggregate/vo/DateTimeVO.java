package com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Embeddable
public class DateTimeVO {
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time",  nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time",  nullable = false)
    private LocalTime endTime;

    protected DateTimeVO() {}

    public DateTimeVO(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
