package com.spinetracker.spinetracker.domain.member.command.application.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@ToString
public class CreateMemberInfoDTO {

    private String gender;
    private LocalDate birthdate;
    private String job;

    public CreateMemberInfoDTO(String gender, LocalDate birthdate, String job) {
        this.gender = gender;
        this.birthdate = birthdate;
        this.job = job;
    }
}
