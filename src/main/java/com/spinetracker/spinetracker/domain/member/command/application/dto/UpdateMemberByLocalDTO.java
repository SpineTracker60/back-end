package com.spinetracker.spinetracker.domain.member.command.application.dto;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@ToString
public class UpdateMemberByLocalDTO {

    @NotBlank(message = "PASSWORD_IS_MANDATORY")
    @NotNull
    private String password;
    private String role;
    @NotBlank(message = "NAME_IS_NOT_BLANK")
    private String name;
    @NotBlank(message = "GENDER_IS_NOT_BLANK")
    private String gender;
    @NotBlank(message = "AGERANGE_IS_NOT_BLANK")
    private String ageRange;
    @NotBlank(message = "JOB_IS_NOT_BLANK")
    private String job;

    public UpdateMemberByLocalDTO(String password, String role, String name, String gender, String ageRange, String job) {
        this.password = password;
        this.role = role;
        this.name = name;
        this.gender = gender;
        this.ageRange = ageRange;
        this.job = job;
    }

    public UpdateMemberByLocalDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public UpdateMemberByLocalDTO setRole(String role) {
        this.role = role;
        return this;
    }

    public UpdateMemberByLocalDTO setName(String name) {
        this.name = name;
        return this;
    }

    public UpdateMemberByLocalDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public UpdateMemberByLocalDTO setAgeRange(String ageRange) {
        this.ageRange = ageRange;
        return this;
    }

    public UpdateMemberByLocalDTO setJob(String job) {
        this.job = job;
        return this;
    }
}
