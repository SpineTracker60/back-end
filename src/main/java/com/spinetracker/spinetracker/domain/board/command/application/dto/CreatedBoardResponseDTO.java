package com.spinetracker.spinetracker.domain.board.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class CreatedBoardResponseDTO {

    @Schema(type = "Long", example = "1", description = "새로 생성된 게시판의 번호입니다.")
    @JsonProperty("board_id")
    private final Long boardId;

    public CreatedBoardResponseDTO(Long boardId) {
        this.boardId = boardId;
    }
}
