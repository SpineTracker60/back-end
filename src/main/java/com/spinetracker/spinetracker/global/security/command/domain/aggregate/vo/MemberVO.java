package com.spinetracker.spinetracker.global.security.command.domain.aggregate.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class MemberVO {

    @Column(nullable = false, name = "member_id")
    private Long id;

    protected MemberVO() {
    }

    public MemberVO(Long id) {
        this.id = id;
    }
}