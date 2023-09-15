package com.spinetracker.spinetracker.domain.chatroom.command.infra.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChatRoomResponse implements Serializable {
    private Boolean success;
    @JsonProperty("body")
    private ChatRoomBody chatRoomBody;

    public ChatRoomResponse() {}
    @Override
    public String toString() {
        return "ChatRoomResponse{" +
                "status=" + success +
                ", chatRoomBody=" + chatRoomBody +
                '}';
    }
}
