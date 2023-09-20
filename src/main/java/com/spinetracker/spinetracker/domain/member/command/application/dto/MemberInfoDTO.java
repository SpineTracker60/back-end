package com.spinetracker.spinetracker.domain.member.command.application.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class MemberInfoDTO {
    private Long id;
    private String gender;
    private LocalDate birthdate;
    private String job;

    public MemberInfoDTO() {}

    public MemberInfoDTO(Long id, String gender, LocalDate birthdate, String job) {
        this.id = id;
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
