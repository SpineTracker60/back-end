package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.MemberInfoVO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateMemberBySocialDTO {
    private String UID;
    private String profileImage;
    private RoleEnum role;
    private PlatformEnum platformEnum;
    private MemberInfoVO memberInfo;

    public UpdateMemberBySocialDTO(String UID, String profileImage, RoleEnum role, PlatformEnum platformEnum, MemberInfoVO memberInfo) {
        this.UID = UID;
        this.profileImage = profileImage;
        this.role = role;
        this.platformEnum = platformEnum;
        this.memberInfo = memberInfo;
    }

    public UpdateMemberBySocialDTO setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public UpdateMemberBySocialDTO setRole(RoleEnum role) {
        this.role = role;
        return this;
    }

    public UpdateMemberBySocialDTO setMemberInfo(MemberInfoVO memberInfo) {
        this.memberInfo = memberInfo;
        return this;
    }
}
