package com.spinetracker.spinetracker.domain.member.query.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMemberDTO {

    private long id;

    private String name;

    private String gender;

    private String ageRange;

    private String job;

    private String profileImage;

    private String platform;

    private String role;

    private String email;

    protected FindMemberDTO() {}


    public FindMemberDTO(long id, String name, String gender, String ageRange, String job, String profileImage, String platform, String role, String email) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.ageRange = ageRange;
        this.job = job;
        this.profileImage = profileImage;
        this.platform = platform;
        this.role = role;
        this.email = email;
    }
}
