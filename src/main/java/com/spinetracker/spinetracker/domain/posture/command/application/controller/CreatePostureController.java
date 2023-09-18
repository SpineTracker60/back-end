package com.spinetracker.spinetracker.domain.posture.command.application.controller;

import com.spinetracker.spinetracker.domain.chatroom.command.infra.response.Response;
import com.spinetracker.spinetracker.domain.posture.command.application.dto.CreatePostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.command.application.dto.PostureBody;
import com.spinetracker.spinetracker.domain.posture.command.application.dto.ResponsePosture;
import com.spinetracker.spinetracker.domain.posture.command.application.service.CreatePostureLogService;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.entity.PostureLog;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posture")
public class CreatePostureController {

    private final CreatePostureLogService createPostureLogService;

    @Autowired
    public CreatePostureController(CreatePostureLogService createPostureLogService) {
        this.createPostureLogService = createPostureLogService;
    }

    @PostMapping
    public ResponseEntity<ResponsePosture> create(@RequestBody List<CreatePostureLogDTO> createPostureLogDTO, @CurrentMember UserPrincipal userPrincipal) {

        Long memberId = userPrincipal.getId();
        List<PostureLog> createdPostureLogList = createPostureLogService.create(memberId, createPostureLogDTO);
        List<PostureBody> postureBodyList = getPostureBodies(createdPostureLogList);
        ResponsePosture responsePosture = new ResponsePosture(
                ! postureBodyList.isEmpty(),
                postureBodyList
        );
        return ResponseEntity.created(URI.create("/posture")).body(responsePosture);
    }

    private List<PostureBody> getPostureBodies(List<PostureLog> createdPostureLogList) {
        List<PostureBody> postureBodyList = new ArrayList<>();

        for (PostureLog postureLog: createdPostureLogList) {

            PostureBody postureBody = new PostureBody(
                    postureLog.getMember().getId(),
                    postureLog.getPostureTag().name(),
                    postureLog.getDateTime().getDate(),
                    postureLog.getDateTime().getStartTime(),
                    postureLog.getDateTime().getEndTime()
            );
            postureBodyList.add(postureBody);
        }
        return postureBodyList;
    }
}
