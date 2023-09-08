package com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype;

import lombok.Getter;

@Getter
public enum RoleEnum {
    GUEST("ROLE_GUEST", "손님"),
    MEMBER("ROLE_MEMBER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;

    RoleEnum(String key, String title) {
        this.key = key;
        this.title = title;
    }
}
