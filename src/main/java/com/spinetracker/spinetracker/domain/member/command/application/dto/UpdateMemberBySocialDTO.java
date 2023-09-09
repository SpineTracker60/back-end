package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.MemberInfoVO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateMemberBySocialDTO {
    private String profileImage;
    private RoleEnum role;
    private MemberInfoVO memberInfo;

    public UpdateMemberBySocialDTO(String profileImage, RoleEnum role, MemberInfoVO memberInfo) {
        this.profileImage = profileImage;
        this.role = role;
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
