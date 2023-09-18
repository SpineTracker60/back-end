package com.spinetracker.spinetracker.domain.chatroom.command.infra;

import com.spinetracker.spinetracker.domain.chatroom.command.application.dto.FindChatroomDTO;
import com.spinetracker.spinetracker.domain.chatroom.command.domain.service.RequestChatroom;
import com.spinetracker.spinetracker.domain.chatroom.command.infra.response.ChatRoomBody;
import com.spinetracker.spinetracker.domain.chatroom.command.infra.response.ChatRoomResponse;
import com.spinetracker.spinetracker.domain.chatroom.command.infra.response.Response;
import com.spinetracker.spinetracker.global.common.annotation.InfraService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Mono;

import java.util.Objects;


@InfraService
public class RequestChatroomImpl implements RequestChatroom {

    private final WebClient webClient;
    public RequestChatroomImpl(@Value("${spring.app.chatHost}") String nodeUrl) {
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(nodeUrl);
        webClient = WebClient.builder().uriBuilderFactory(uriBuilderFactory).baseUrl(nodeUrl).build();
    }

    @Override
    public FindChatroomDTO create(Long memberId) {

        Mono<Response> response = webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/room")
                        .queryParam("memberId", memberId)
                        .build()
                ).accept(MediaType.APPLICATION_JSON)
                        .exchangeToMono(response1 -> {
                            if (response1.statusCode().is2xxSuccessful()) {
                                return response1.bodyToMono(Response.class);
                            } else {
                                return response1.createException().flatMap(Mono::error);
                            }
                        });

        /*
            Response(status=true, chatRoomResponse=ChatRoomResponse{status=false, chatRoomBody=ChatRoomBody{member='null', note='null', id='null', deleted=null, createdAt=null, updatedAt=null}})
         */
        ChatRoomBody chatRoomBody = Objects.requireNonNull(response.block()).getChatRoomResponse().getChatRoomBody();

        return new FindChatroomDTO(chatRoomBody.getId(), chatRoomBody.getCreatedAt(), chatRoomBody.getMember());
    }
}
