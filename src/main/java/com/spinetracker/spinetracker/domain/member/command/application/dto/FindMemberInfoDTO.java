package com.spinetracker.spinetracker.domain.member.command.application.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class FindMemberInfoDTO {
    private Long id;
    private String gender;
    private LocalDate birthdate;
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
