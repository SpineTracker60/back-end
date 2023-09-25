package com.spinetracker.spinetracker.domain.posture.command.infra.service;

import com.spinetracker.spinetracker.domain.posture.command.domain.service.RequestPostFcmMessage;
import com.spinetracker.spinetracker.global.common.annotation.InfraService;
import com.spinetracker.spinetracker.infra.firebase.command.infra.service.PostMessageService;
import org.springframework.beans.factory.annotation.Autowired;

@InfraService
public class RequestPostFcmMessageImpl  implements RequestPostFcmMessage {

    private final PostMessageService postMessageService;

    @Autowired
    public RequestPostFcmMessageImpl(PostMessageService postMessageService) {
        this.postMessageService = postMessageService;
    }

    @Override
    public boolean send(String title, String body, String receiverToken) {
        return postMessageService.sendToToken(title, body, receiverToken);
    }
}
