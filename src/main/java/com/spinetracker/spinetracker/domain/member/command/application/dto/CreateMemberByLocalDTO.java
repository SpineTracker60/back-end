package com.spinetracker.spinetracker.domain.member.command.application.dto;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.MemberInfoVO;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateMemberByLocalDTO {
    private final String email;
    private final String password;
    private final RoleEnum role;
    private final MemberInfoVO memberInfo;

    public CreateMemberByLocalDTO(String email, String password, RoleEnum role, MemberInfoVO memberInfo) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.memberInfo = memberInfo;
    }
}
