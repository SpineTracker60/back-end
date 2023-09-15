package com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class MemberVO {

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    protected MemberVO() {}

    public MemberVO(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
