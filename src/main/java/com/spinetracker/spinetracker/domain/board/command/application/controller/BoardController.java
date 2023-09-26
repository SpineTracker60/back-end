package com.spinetracker.spinetracker.domain.board.command.application.controller;

import com.spinetracker.spinetracker.domain.board.command.application.dto.CreatePostDTO;
import com.spinetracker.spinetracker.domain.board.command.application.dto.UpdatePostDTO;
import com.spinetracker.spinetracker.domain.board.command.application.service.CreateBoardService;
import com.spinetracker.spinetracker.domain.board.command.application.service.DeleteBoardService;
import com.spinetracker.spinetracker.domain.board.command.application.service.UpdateBoardService;
import com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.Board;
import com.spinetracker.spinetracker.domain.board.query.application.dto.FindBoardDTO;
import com.spinetracker.spinetracker.domain.board.query.application.dto.FindProductDTO;
import com.spinetracker.spinetracker.domain.board.query.application.service.FindProductService;
import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import com.spinetracker.spinetracker.domain.member.query.application.service.FindMemberService;
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
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Board", description = "게시판 관련 API")
@RestController
@RequestMapping("/board")
public class BoardController {

    private final CreateBoardService createBoardService;
    private final UpdateBoardService updateBoardService;
    private final DeleteBoardService deleteBoardService;

    private final FindProductService findProductService;
    private final FindMemberService findMemberService;

    @Autowired
    public BoardController(CreateBoardService createBoardService, UpdateBoardService updateBoardService, DeleteBoardService deleteBoardService, FindProductService findProductService, FindMemberService findMemberService) {
        this.createBoardService = createBoardService;
        this.updateBoardService = updateBoardService;
        this.deleteBoardService = deleteBoardService;
        this.findProductService = findProductService;
        this.findMemberService = findMemberService;
    }

    @Operation(
            summary = "게시판 글 작성",
            description = "게시판에 글을 등록 합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = FindBoardDTO.class))}),
    })
    @PostMapping
    public ResponseEntity<FindBoardDTO> createPost(@CurrentMember UserPrincipal userPrincipal, @RequestBody CreatePostDTO createPostDTO) {

        Long memberId = userPrincipal.getId();

        Board createdBoard = createBoardService.createPost(memberId, createPostDTO);

        FindProductDTO findProduct = findProductService.findById(createdBoard.getProduct().getId());
        FindMemberDTO findMember = findMemberService.findById(createdBoard.getWriter().getId());
        FindBoardDTO findBoardDTO = new FindBoardDTO(
                        createdBoard.getId(),
                        createdBoard.getWriter().getId(),
                        findMember.getName(),
                        findMember.getProfileImage(),
                        createdBoard.getContent(),
                        findProduct.getProductName(),
                        findProduct.getProductUrl(),
                        findProduct.getImageUrl()
                );
        return ResponseEntity.created(URI.create("/board"))
                .body(findBoardDTO);
    }

    @Operation(
            summary = "게시판 글 수정",
            description = "게시판의 글을 수정 합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = UpdatePostDTO.class))}),
    })
    @PutMapping("/{boardId}")
    public ResponseEntity<UpdatePostDTO> updatePost(@CurrentMember UserPrincipal userPrincipal, @PathVariable Long boardId, @RequestBody UpdatePostDTO updatePostDTO) {

        Long memberId = userPrincipal.getId();

        Board updatedBoard = updateBoardService.updatePost(memberId, boardId, updatePostDTO);

        return ResponseEntity.ok().body(new UpdatePostDTO(
                updatedBoard.getContent(),
                updatedBoard.getCreatedDate()));
    }

    @Operation(
            summary = "게시판 글 삭제",
            description = "게시판의 글을 삭제 합니다."
    )
    // response 정보
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "noContent"),
    })

    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deletePost(@CurrentMember UserPrincipal userPrincipal, @PathVariable Long boardId) {

        Long memberId = userPrincipal.getId();

        deleteBoardService.deletePost(boardId, memberId);

        return ResponseEntity.noContent().build();
    }
}
