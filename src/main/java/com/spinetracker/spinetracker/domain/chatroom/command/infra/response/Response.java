package com.spinetracker.spinetracker.domain.chatroom.command.infra.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response {
    private Boolean status;
    @JsonProperty("body")
    private ChatRoomResponse chatRoomResponse;

    public Response() {}
}
