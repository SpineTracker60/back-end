package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreatedMemberInfoResponseDTO {

    @Schema(type = "String", example = "FEMALE", description = "사용자 성별 입니다.", allowableValues = {"FEMALE", "MALE", "ETC"})
    private String gender;

    @Schema(type = "String", example = "2023-09-20", description = "사용자 생년월일 입니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="Asia/Seoul")
    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @Schema(type = "String", example = "student", description = "사용자 직업 입니다.")
    private String job;

    @Schema(type = "long", example = "1", description = "사용자 아이디 입니다.")
    @JsonProperty("member_id")
    private Long memberId;

    @Schema(type = "String", example = "UNDER_TEN", description = "사용자 나이대 입니다.",
            allowableValues = {"RANGE_UNDER_TEN", "RANGE_TEN", "RANGE_TWENTY", "RANGE_THIRSTY", "RANGE_FORTY", "RANGE_FIFTY", "RANGE_SIXTY"})
    @JsonProperty("age_range")
    private String ageRange;

    public CreatedMemberInfoResponseDTO(String gender, LocalDate birthDate, String job, Long memberId, String ageRange) {
        this.gender = gender;
        this.birthDate = birthDate;
        this.job = job;
        this.memberId = memberId;
        this.ageRange = ageRange;
    }
}
