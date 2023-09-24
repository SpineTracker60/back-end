package com.spinetracker.spinetracker.infra.firebase.command.domain.entity.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MemberVO {

    @Column(nullable = false, name = "member_id", unique = true)
    private Long id;

    protected MemberVO() {}

    public MemberVO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "id=" + id +
                '}';
    }
}
