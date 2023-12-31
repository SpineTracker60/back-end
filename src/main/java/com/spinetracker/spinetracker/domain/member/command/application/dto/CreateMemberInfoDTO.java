package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Getter
@ToString
@Setter
public class CreateMemberInfoDTO {

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

    public CreateMemberInfoDTO() {}

    public CreateMemberInfoDTO(String gender, LocalDate birthdate, String job) {
        this.gender = gender;
        this.birthdate = birthdate;
        this.job = job;
    }
}
