package com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.enumtype;

import lombok.Getter;

@Getter
public enum PostureTag {

    TEXTNECK("거북목"),
    ASYMMETRY("비대칭"),
    STOOPED ("구부정"),
    SLEEPINESS ("졸음"),
    START ("시작"),
    END ("종료");

    private final String title;
    PostureTag(String title) {
        this.title = title;
    }
}
