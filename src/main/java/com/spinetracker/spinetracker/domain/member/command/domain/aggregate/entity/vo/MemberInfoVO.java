package com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.AgeRangeEnum;
import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.GenderEnum;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class MemberInfoVO {

    @Column(length = 100, nullable = false, name="name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="gender")
    private GenderEnum gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="age_range")
    private AgeRangeEnum ageRange;

    @Column(nullable = false, name="job")
    private String job;

    protected MemberInfoVO() {}


    public MemberInfoVO(String name, GenderEnum gender, AgeRangeEnum ageRange, String job) {
        this.name = name;
        this.gender = gender;
        this.ageRange = ageRange;
        this.job = job;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public MemberInfoVO setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public AgeRangeEnum getAgeRange() {
        return ageRange;
    }

    public MemberInfoVO setAgeRange(AgeRangeEnum ageRange) {
        this.ageRange = ageRange;
        return this;
    }

    public String getJob() {
        return job;
    }

    public MemberInfoVO setJob(String job) {
        this.job = job;
        return this;
    }

    public String getName() {
        return name;
    }

    public MemberInfoVO setName(String name) {
        this.name = name;
        return this;
    }
}