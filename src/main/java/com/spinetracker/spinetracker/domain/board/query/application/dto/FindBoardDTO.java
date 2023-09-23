package com.spinetracker.spinetracker.domain.board.query.application.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FindBoardDTO {
    private final Long writerId;
    private final String writerName;
    private final String content;
    private final String productName;
    private final String productUrl;
    private final String imageUrl;

    public FindBoardDTO(Long writerId, String writerName, String content, String productName, String productUrl, String imageUrl) {
        this.writerId = writerId;
        this.writerName = writerName;
        this.content = content;
        this.productName = productName;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
    }
}
