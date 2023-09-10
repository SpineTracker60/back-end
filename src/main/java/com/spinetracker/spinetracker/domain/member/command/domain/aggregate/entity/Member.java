package com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.PlatformEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.RoleEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.MemberInfoVO;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="MEMBER_TB")
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 300, nullable = true)
    private String password;

    @Column(nullable = true, name="uid")
    private String UID;

    @Column(length = 500, nullable = true, name="profile_image")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private PlatformEnum platform;

    @CreatedDate
    @Column(nullable = false, name="created_date")
    private LocalDateTime createdDate;

    @Embedded
    private MemberInfoVO memberInfo;


    protected Member() {}

    // 자체 로그인 시
    public Member(String email, String password, RoleEnum role, MemberInfoVO memberInfo) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdDate = LocalDateTime.now();
        this.memberInfo = memberInfo;
    }

    // 소셜 로그인 시
    public Member(String email, String UID, String profileImage, RoleEnum role, PlatformEnum platform, MemberInfoVO memberInfo) {
        this.email = email;
        this.UID = UID;
        this.profileImage = profileImage;
        this.role = role;
        this.platform = platform;
        this.createdDate = LocalDateTime.now();
        this.memberInfo = memberInfo;
    }

    public Member setEmail(String email) {
        this.email = email;
        return this;
    }

    public Member setPassword(String password) {
        this.password = password;
        return this;
    }

    public Member setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public Member setRole(RoleEnum role) {
        this.role = role;
        return this;
    }

    public void setMemberInfo(MemberInfoVO memberInfo) {
        this.memberInfo = memberInfo;
    }
}
