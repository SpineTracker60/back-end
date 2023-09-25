package com.spinetracker.spinetracker.infra.firebase.query.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindFcmTokenDTO {
    private Long id;
    private Long memberId;
    private String FcmToken;

    public FindFcmTokenDTO() {}

    public FindFcmTokenDTO(Long id, Long memberId, String fcmToken) {
        this.id = id;
        this.memberId = memberId;
        FcmToken = fcmToken;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setFcmToken(String fcmToken) {
        FcmToken = fcmToken;
    }
}
