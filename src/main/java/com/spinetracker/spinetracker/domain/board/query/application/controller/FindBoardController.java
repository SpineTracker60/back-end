package com.spinetracker.spinetracker.domain.board.query.application.controller;

import com.spinetracker.spinetracker.domain.board.query.application.dto.FindBoardDTO;
import com.spinetracker.spinetracker.domain.board.query.application.dto.FindPostDTO;
import com.spinetracker.spinetracker.domain.board.query.application.service.FindBoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Tag(name = "Board", description = "게시판 조회 관련 API")
@RestController
@RequestMapping("/board")
public class FindBoardController {

    private final FindBoardService findBoardService;

    @Autowired
    public FindBoardController(FindBoardService findBoardService) {
        this.findBoardService = findBoardService;
    }

    @Operation(
            summary = "게시판 글 전체 조회",
            description = "게시판에 등록되어있는 글을 전체 조회 합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FindBoardDTO.class))}),
    })
    @GetMapping("/list")
    public ResponseEntity<List<FindBoardDTO>> getBoardInfo() {

        List<FindBoardDTO> findBoard = findBoardService.findAllPost();

        return ResponseEntity.ok().body(findBoard);
    }

    @Operation(
            summary = "게시판 글 상세 조회",
            description = "게시판에 등록되어있는 글을 상세 조회 합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = FindPostDTO.class))}),
    })
    @GetMapping("/{boardId}")
    public ResponseEntity<FindPostDTO> getBoardNo(@PathVariable Long boardId) {

        FindPostDTO findPostDTO = findBoardService.findBoardNo(boardId);

        return ResponseEntity.ok().body(findPostDTO);
    }
}
