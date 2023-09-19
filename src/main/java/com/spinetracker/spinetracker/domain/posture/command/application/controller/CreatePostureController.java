package com.spinetracker.spinetracker.domain.posture.command.application.controller;

import com.spinetracker.spinetracker.domain.posture.command.application.dto.CreatePostureLogDTO;
import com.spinetracker.spinetracker.domain.posture.command.application.dto.PostureBody;
import com.spinetracker.spinetracker.domain.posture.command.application.dto.ResponsePosture;
import com.spinetracker.spinetracker.domain.posture.command.application.service.CreatePostureLogService;
import com.spinetracker.spinetracker.domain.posture.command.domain.aggregate.entity.PostureLog;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "PostureLog", description = "자세 기록 API")
@RestController
@RequestMapping("/posture")
public class CreatePostureController {

    private final CreatePostureLogService createPostureLogService;

    @Autowired
    public CreatePostureController(CreatePostureLogService createPostureLogService) {
        this.createPostureLogService = createPostureLogService;
    }

    // 메서드 정보
    @Operation(
            summary = "자세 기록 생성",
            description = "토큰을 기반으로 사용자의 새로운 자세 트래킹 정보를 셍성합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ResponsePosture.class))}),
            //@ApiResponse(code = 204, message = "member not exists"),
    })
    @PostMapping("/")
    public ResponseEntity<ResponsePosture> create(
            @RequestBody List<CreatePostureLogDTO> createPostureLogDTOList,
            @CurrentMember UserPrincipal userPrincipal
    ) {

        Long memberId = userPrincipal.getId();
        List<PostureLog> createdPostureLogList = new ArrayList<>();
        for(CreatePostureLogDTO createPostureLogDTO: createPostureLogDTOList) {
            createdPostureLogList.add(createPostureLogService.create(memberId, createPostureLogDTO));
        }

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
