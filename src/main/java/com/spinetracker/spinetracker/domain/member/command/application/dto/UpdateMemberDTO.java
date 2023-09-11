package com.spinetracker.spinetracker.domain.member.command.application.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateMemberDTO {
    private String profileImage;
    private String name;

    public UpdateMemberDTO(String profileImage, String name) {
        this.profileImage = profileImage;
        this.name = name;
    }

    public UpdateMemberDTO setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public UpdateMemberDTO setName(String name) {
        this.name = name;
        return this;
    }
}
