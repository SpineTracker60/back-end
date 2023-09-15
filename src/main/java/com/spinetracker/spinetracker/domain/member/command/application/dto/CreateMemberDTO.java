package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class CreateMemberDTO {
    private final String email;
    private final String UID;
    private final String profileImage;
    private final String role;
    private final PlatformEnum platformEnum;
    @NotBlank(message = "NAME_IS_NOT_BLANK")
    private final String name;

    public CreateMemberDTO(String email, String UID, String profileImage, PlatformEnum platformEnum, String name) {
        this.email = email;
        this.UID = UID;
        this.profileImage = profileImage;
        this.role = "MEMBER";
        this.platformEnum = platformEnum;
        this.name = name;
    }
}
