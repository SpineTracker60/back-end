package com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.vo;

import com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype.AgeRangeEnum;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Embeddable
public class AgeRangeVO {

    @Enumerated(EnumType.STRING)
    @Column(name = "age_range")
    private AgeRangeEnum ageRange;

    protected AgeRangeVO() {}

    public AgeRangeVO(LocalDate birthdate) {
        int age;    // 기본 타입을 객체 형태로 바꿈
        LocalDate now = LocalDate.now();
        age = now.minusYears(birthdate.getYear()).getYear();
        if(birthdate.plusYears(age).isAfter(now)) {
            age = age - 1;
        }

        String ageString = Integer.toString(age);
        if (ageString.length() >=2 ) {
            String sub = ageString.substring(0, 1);
            switch (sub) {
                case "1":
                    ageRange = AgeRangeEnum.TEN;
                    break;
                case "2":
                    ageRange = AgeRangeEnum.TWENTY;
                    break;
                case "3":
                    ageRange = AgeRangeEnum.THIRSTY;
                    break;
                case "4":
                    ageRange = AgeRangeEnum.FORTY;
                    break;
                case "5":
                    ageRange = AgeRangeEnum.FIFTY;
                    break;
                default:
                    ageRange = AgeRangeEnum.SIXTY;
                    break;
            }
        } else {
            ageRange = AgeRangeEnum.UNDER_TEN;
        }
    }
}
