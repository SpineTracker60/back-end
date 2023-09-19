package com.spinetracker.spinetracker.domain.posture.query.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

@JsonPropertyOrder({ "focus_time", "unstable_time", "count_sleep", "count_warn", "posture_time" })
public class DailyPostureLogDTO implements Serializable {

    @JsonProperty("focus_time")
    @Schema(type = "Integer", example = "7200", description="일간 집중 시간입니다. (단위 : 초)")
    private Integer focusTime;

    @JsonProperty("unstable_time")
    @Schema(type = "Integer", example = "541", description="일간 불안정 자세입니다. (단위 : 초)")
    private Integer unstableTime;

    @JsonProperty("count_sleep")
    @Schema(type = "Integer", example = "4", description="일간 졸음 횟수입니다. (단위 : 회)")
    private Integer CountSleep;

    @JsonProperty("count_warn")
    @Schema(type = "Integer", example = "5", description="일간 경고 횟수입니다. (단위 : 회)")
    private Integer CountWarn;

    @JsonProperty("posture_time")
    @Schema(type = "Object", ref = "#/components/schemas/PostureTime")
    private Map<String, Integer> postureTime;

    protected DailyPostureLogDTO() {}

    public DailyPostureLogDTO(Integer focusTime, Integer unstableTime, Integer countSleep, Integer countWarn, Map<String, Integer> postureTime) {
        this.focusTime = focusTime;
        this.unstableTime = unstableTime;
        this.CountSleep = countSleep;
        this.CountWarn = countWarn;
        this.postureTime = postureTime;
    }

    @Override
    public String toString() {
        return "DailyPostureLogDTO{" +
                "focusTime=" + focusTime +
                ", unstableTime=" + unstableTime +
                ", CountSleep=" + CountSleep +
                ", CountWarn=" + CountWarn +
                ", postureTime=" + postureTime +
                '}';
    }
}