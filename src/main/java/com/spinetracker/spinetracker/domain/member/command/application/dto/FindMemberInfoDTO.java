package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class FindMemberInfoDTO {

    @Schema(type = "Long", example = "1", description = "사용자 정보 번호 입니다.")
    private Long id;

    @Schema(type = "String", example = "FEMALE", description = "사용자 성별 입니다.")
    @NotBlank
    @NotNull
    private String gender;

    @Schema(type = "String", example = "2023-09-20", description = "사용자 생년월일 입니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="Asia/Seoul")
    @JsonProperty("birth_date")
    @NotBlank
    @NotNull
    private LocalDate birthdate;

    @Schema(type = "String", example = "student", description = "사용자 직업 입니다.")
    @NotBlank
    @NotNull
    private String job;

    public FindMemberInfoDTO() {}

    public FindMemberInfoDTO(Long id, String gender, LocalDate birthdate, String job) {
        this.id = id;
        this.gender = gender;
        this.birthdate = birthdate;
        this.job = job;
    }

    public FindMemberInfoDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public FindMemberInfoDTO setAgeRange(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public FindMemberInfoDTO setJob(String job) {
        this.job = job;
        return this;
    }
}
