package com.spinetracker.spinetracker.domain.posture.command.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class PostureBody implements Serializable {
    private Long memberId;
    @JsonProperty("posture_tag")
    @Schema(type = "String", example = "TEXTNECK", description="자세 태그 입니다.", allowableValues={"TEXTNECK", "ASYMMETRY", "STOOPED", "SLEEPINESS", "START", "END"})
    private String postureTag;

    @Schema(type = "String", example = "2023-09-18", description="한 자세 태그의 생성 일자 입니다." )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="Asia/Seoul")
    private LocalDate date;

    @JsonProperty("start_time")
    @Schema(type = "String", example = "12:00:00", description="한 자세 태그의 시작 시간 입니다." )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone="Asia/Seoul")
    private LocalTime startTime;

    @JsonProperty("end_time")
    @Schema(type = "String", example = "12:00:00", description="한 자세 태그의 종료 시간 입니다." )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone="Asia/Seoul")
    private LocalTime endTime;

    public PostureBody(Long memberId, String postureTag, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.memberId = memberId;
        this.postureTag = postureTag;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
