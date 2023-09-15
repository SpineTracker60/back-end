package com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.AgeRangeEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.GenderEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.AgeRangeVO;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo.MemberVO;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "MEMBERINFO_TB")
@Getter
public class MemberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="gender")
    private GenderEnum gender;

    @NotNull
    @Column(nullable = false, name="birth_date")
    private LocalDate birthDate;

    @NotNull
    @Column(nullable = false, name="job")
    private String job;

    @Embedded
    private MemberVO memberVO;

    protected MemberInfo() {}


    public MemberInfo(GenderEnum gender, LocalDate birthDate, String job, MemberVO memberVO) {
        this.gender = gender;
        this.birthDate = birthDate;
        this.job = job;
        this.memberVO = memberVO;
    }

    public MemberInfo setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public MemberInfo setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public MemberInfo setJob(String job) {
        this.job = job;
        return this;
    }

    public AgeRangeEnum getAgeRange() {
        return new AgeRangeVO(this.birthDate).getAgeRange();
    }
}