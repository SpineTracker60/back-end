package com.spinetracker.spinetracker.domain.member.command.application.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class MemberInfoDTO {
    private String gender;
    private LocalDate birthdate;
    private String job;

    public MemberInfoDTO() {}

    public MemberInfoDTO(String gender, LocalDate birthdate, String job) {
        this.gender = gender;
        this.birthdate = birthdate;
        this.job = job;
    }

    public MemberInfoDTO setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public MemberInfoDTO setAgeRange(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public MemberInfoDTO setJob(String job) {
        this.job = job;
        return this;
    }
}
