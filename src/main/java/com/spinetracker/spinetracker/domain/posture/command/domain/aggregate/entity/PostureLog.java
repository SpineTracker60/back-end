package com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.entity;

import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.enumtype.PostureTag;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.vo.DateTimeVO;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.vo.MemberVO;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "POSTURE_LOG_TB")
@Getter
public class PostureLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private MemberVO member;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "posture_tag", nullable = false)
    private PostureTag postureTag;

    @Embedded
    private DateTimeVO dateTime;

    protected PostureLog() {}

    public PostureLog(MemberVO member, PostureTag postureTag, DateTimeVO dateTime) {
        this.member = member;
        this.postureTag = postureTag;
        this.dateTime = dateTime;
    }
}
