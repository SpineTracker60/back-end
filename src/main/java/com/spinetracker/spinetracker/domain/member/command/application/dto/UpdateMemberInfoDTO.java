package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class UpdateMemberInfoDTO {


    @Schema(type = "String", example = "FEMALE", description = "사용자 성별 입니다.", allowableValues = {"FEMALE", "MALE", "ETC"})
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

    @Schema(type = "long", example = "1", description = "사용자 아이디 입니다.")
    @JsonProperty("member_id")
    private Long memberId;

    public UpdateMemberInfoDTO() {
    }

    public UpdateMemberInfoDTO(String gender, LocalDate birthdate, String job, Long memberId) {
        this.gender = gender;
        this.birthdate = birthdate;
        this.job = job;
        this.memberId = memberId;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
