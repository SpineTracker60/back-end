package com.spinetracker.spinetracker.domain.chatroom.command.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nimbusds.jose.shaded.json.parser.ParseException;
import com.spinetracker.spinetracker.domain.chatroom.command.application.dto.FindChatroomDTO;
import com.spinetracker.spinetracker.domain.posture.command.application.dto.CreatePostureDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RequestChatroomServiceTest {

    @Autowired
    private RequestChatroomService requestChatroomService;

    private static Stream<Arguments> getMemberIdInfo() {
        return Stream.of(
                Arguments.of(
                        0L
                )
        );
    }

    @DisplayName("memberId를 통해 채팅방이 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("getMemberIdInfo")
    void createChatRoomRequestTest(Long memberId) {
        FindChatroomDTO createdChatroomDTO = requestChatroomService.create(memberId);
    }
}