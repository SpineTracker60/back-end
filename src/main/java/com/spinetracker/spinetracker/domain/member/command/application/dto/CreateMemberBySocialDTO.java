package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.MemberInfoVO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateMemberBySocialDTO {
    private final String email;
    private final String UID;
    private final String profileImage;
    private final RoleEnum role;
    private final PlatformEnum platformEnum;
    private final MemberInfoVO memberInfo;

    public CreateMemberBySocialDTO(String email, String UID, String profileImage, RoleEnum role, PlatformEnum platformEnum, MemberInfoVO memberInfo) {
        this.email = email;
        this.UID = UID;
        this.profileImage = profileImage;
        this.role = role;
        this.platformEnum = platformEnum;
        this.memberInfo = memberInfo;
    }
}
