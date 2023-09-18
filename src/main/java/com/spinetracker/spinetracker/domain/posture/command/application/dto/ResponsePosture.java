package com.spinetracker.spinetracker.domain.posture.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spinetracker.spinetracker.domain.chatroom.command.infra.response.ChatRoomBody;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponsePosture {
    private Boolean success;
    @JsonProperty("results")
    private List<PostureBody> postureBody;

    public ResponsePosture(Boolean success, List<PostureBody> postureBody) {
        this.success = success;
        this.postureBody = postureBody;
    }
}
