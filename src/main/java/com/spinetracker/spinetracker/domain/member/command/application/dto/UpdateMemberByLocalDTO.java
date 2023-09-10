package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.MemberInfoVO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateMemberByLocalDTO {
    private String password;
    private RoleEnum role;
    private MemberInfoVO memberInfo;

    public UpdateMemberByLocalDTO(String password, RoleEnum role, MemberInfoVO memberInfo) {
        this.password = password;
        this.role = role;
        this.memberInfo = memberInfo;
    }

    public UpdateMemberByLocalDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public UpdateMemberByLocalDTO setRole(RoleEnum role) {
        this.role = role;
        return this;
    }

    public UpdateMemberByLocalDTO setMemberInfo(MemberInfoVO memberInfo) {
        this.memberInfo = memberInfo;
        return this;
    }
}
