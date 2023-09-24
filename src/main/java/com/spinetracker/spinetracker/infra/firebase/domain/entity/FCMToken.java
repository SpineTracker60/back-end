package com.spinetracker.spinetracker.infra.firebase.domain.entity;

import com.spinetracker.spinetracker.infra.firebase.domain.entity.vo.MemberVO;
import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Table(name = "FCM_TOKEN_TB")
public class FCMToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MemberVO member;

    @Column(nullable = false, name = "token")
    private String token;

    protected FCMToken() {}

    public FCMToken(MemberVO member, String token) {
        this.member = member;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "FCMToken{" +
                "id=" + id +
                ", member=" + member +
                ", token='" + token + '\'' +
                '}';
    }
}
