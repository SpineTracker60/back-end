package com.spinetracker.spinetracker.domain.member.command.application.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class CreateMemberByLocalDTO {

    @Email(message = "NOT_VALID_EMAIL")
    private final String email;
    @NotBlank(message = "PASSWORD_IS_MANDATORY")
    @NotNull
    private final String password;
    private final String role;
    @NotBlank(message = "NAME_IS_NOT_BLANK")
    private final String name;
    @NotBlank(message = "GENDER_IS_NOT_BLANK")
    private final String gender;
    @NotBlank(message = "AGERANGE_IS_NOT_BLANK")
    private final String ageRange;
    @NotBlank(message = "JOB_IS_NOT_BLANK")
    private final String job;

    public CreateMemberByLocalDTO(String email, String password, String name, String gender, String ageRange, String job) {
        this.email = email;
        this.password = password;
        this.role = "MEMBER";
        this.name = name;
        this.gender = gender;
        this.ageRange = ageRange;
        this.job = job;
    }
}
