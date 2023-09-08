package com.spinetracker.spinetracker.domain.member.command.domain.aggregate.entity.enumtype;

import lombok.Getter;

@Getter
public enum AgeRangeEnum {

    TEN("RANGE_TEN", "10대"),
    TWENTY("RANGE_TWENTY", "20대"),
    THIRSTY("RANGE_THIRSTY", "30대"),
    FORTY("RANGE_FORTY", "40대"),
    FIFTY("RANGE_FIFTY", "50대"),
    SIXTY("RANGE_SIXTY", "60대");
    private final String key;
    private final String title;

    AgeRangeEnum(String key, String title) {
        this.title = title;
        this.key = key;
    }
}
