package com.spinetracker.spinetracker.domain.board.command.application.controller;

import com.spinetracker.spinetracker.domain.board.command.application.dto.CreatePostDTO;
import com.spinetracker.spinetracker.domain.board.command.application.dto.CreatedBoardResponseDTO;
import com.spinetracker.spinetracker.domain.board.command.application.service.CreateBoardService;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Board;
import com.spinetracker.spinetracker.global.common.annotation.CurrentMember;
import com.spinetracker.spinetracker.global.security.token.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Tag(name = "Board", description = "게시판 관련 API")
@RestController
@RequestMapping("/board")
public class BoardController {

    private final CreateBoardService createBoardService;

    @Autowired
    public BoardController(CreateBoardService createBoardService) {
        this.createBoardService = createBoardService;
    }

    @Operation(
            summary = "게시판 글 작성",
            description = "게시판에 글을 등록합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CreatePostDTO.class))}),
    })
    @PostMapping
    public ResponseEntity<CreatedBoardResponseDTO> createPost(@CurrentMember UserPrincipal userPrincipal, @RequestBody CreatePostDTO createPostDTO) {

        Long memberId = userPrincipal.getId();

        Board createdBoard = createBoardService.createPost(memberId, createPostDTO);

        return ResponseEntity.created(URI.create("/board"))
                .body(new CreatedBoardResponseDTO(
                        createdBoard.getId()
                ));
    }
}
