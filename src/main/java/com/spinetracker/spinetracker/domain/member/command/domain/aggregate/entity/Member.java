package com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity;

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

    @Column(nullable = false, name="uid")
    private String UID;

    @Column(length = 500, nullable = true, name="profile_image")
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleEnum role;

    @CreatedDate
    @Column(nullable = false, name="created_date")
    private LocalDateTime createdDate;

    @Embedded
    private MemberInfoVO memberInfo;


    protected Member() {}

    public Member(String email, String password, String UID, String profileImage, RoleEnum role, MemberInfoVO memberInfo) {
        this.email = email;
        this.password = password;
        this.UID = UID;
        this.profileImage = profileImage;
        this.role = role;
        this.createdDate = LocalDateTime.now();
        this.memberInfo = memberInfo;
    }
}
