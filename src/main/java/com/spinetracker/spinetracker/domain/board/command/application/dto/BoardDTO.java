package com.spinetracker.spinetracker.domain.board.command.application.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardDTO {
    private String title;
    private String content;
    private String productId;
    private String productUrl;
    private boolean boardIsDeleted;

    public BoardDTO(String title, String content, String productId, String productUrl, boolean boardIsDeleted) {
        this.title = title;
        this.content = content;
        this.productId = productId;
        this.productUrl = productUrl;
        this.boardIsDeleted = boardIsDeleted;
    }

    public BoardDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public BoardDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public BoardDTO setBoardIsDeleted(boolean boardIsDeleted) {
        this.boardIsDeleted = boardIsDeleted;
        return this;
    }
}
