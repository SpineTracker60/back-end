package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class CreateMemberBySocialDTO {
    private final String email;
    private final String UID;
    private final String profileImage;
    private final String role;
    private final PlatformEnum platformEnum;
    @NotBlank(message = "NAME_IS_NOT_BLANK")
    private final String name;
    @NotBlank(message = "GENDER_IS_NOT_BLANK")
    private final String gender;
    @NotBlank(message = "AGERANGE_IS_NOT_BLANK")
    private final String ageRange;
    @NotBlank(message = "JOB_IS_NOT_BLANK")
    private final String job;

    public CreateMemberBySocialDTO(String email, String UID, String profileImage, PlatformEnum platformEnum, String name, String gender, String ageRange, String job) {
        this.email = email;
        this.UID = UID;
        this.profileImage = profileImage;
        this.role = "MEMBER";
        this.platformEnum = platformEnum;
        this.name = name;
        this.gender = gender;
        this.ageRange = ageRange;
        this.job = job;
    }
}
