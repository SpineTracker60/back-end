package com.spinetracker.spinetracker.domain.chatroom.command.application.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FindChatroomDTO {

    private final String roomId;
    private final LocalDateTime createdAt;
    private final Long memberId;

    public FindChatroomDTO(String roomId, LocalDateTime createdAt, Long memberId) {
        this.roomId = roomId;
        this.createdAt = createdAt;
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "FindChatroomDTO{" +
                "roomId='" + roomId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", memberId=" + memberId +
                '}';
    }
}
