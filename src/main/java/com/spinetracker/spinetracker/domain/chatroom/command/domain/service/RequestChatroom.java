package com.spinetracker.spinetracker.domain.chatroom.command.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.spinetracker.spinetracker.domain.chatroom.command.application.dto.FindChatroomDTO;

public interface RequestChatroom {

    FindChatroomDTO create(Long memberId);
}
