package com.spinetracker.spinetracker.domain.posture.query.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class WeeklyDashBoardPostureLogDTO {

    @Schema(type = "Date", example = "2023-09-21", description="이번 주에 속한 날짜 입니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="Asia/Seoul")
    private LocalDate date;
    @JsonProperty("daily_posture_summary")
    private DailyPostureLogDTO dailyPostureLogDTO;

    public WeeklyDashBoardPostureLogDTO() {}

    public WeeklyDashBoardPostureLogDTO(LocalDate date, DailyPostureLogDTO dailyPostureLogDTO) {
        this.date = date;
        this.dailyPostureLogDTO = dailyPostureLogDTO;
    }

    @Override
    public String toString() {
        return "WeeklyDashBoardPostureLogDTO{" +
                "date=" + date +
                ", dailyPostureLogDTO=" + dailyPostureLogDTO +
                '}';
    }
}
