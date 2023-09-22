package com.spinetracker.spinetracker.domain.board.command.application.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FindBoardDTO {
    private String title;
    private String content;
    private String productId;
    private String productUrl;

    public FindBoardDTO(String title, String content, String productId, String productUrl) {
        this.title = title;
        this.content = content;
        this.productId = productId;
        this.productUrl = productUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
}
