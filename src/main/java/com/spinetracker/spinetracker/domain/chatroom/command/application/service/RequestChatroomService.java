package com.spinetracker.spinetracker.domain.chatroom.command.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.spinetracker.spinetracker.domain.chatroom.command.application.dto.FindChatroomDTO;
import com.spinetracker.spinetracker.domain.chatroom.command.domain.service.RequestChatroom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestChatroomService {

    private final RequestChatroom requestChatroom;

    @Autowired
    public RequestChatroomService(RequestChatroom requestChatroom) {
        this.requestChatroom = requestChatroom;
    }

    public FindChatroomDTO create(Long memberId) {
        return requestChatroom.create(memberId);
    }
}
