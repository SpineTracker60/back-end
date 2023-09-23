package com.spinetracker.spinetracker.domain.board.command.application.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreatePostDTO {
    private final Long writer;         // 글 작성자
    private final String productName;  // 상품 이름(글 제목)
    private final String content;      // 상품 내용
    private final Long productId;      // 상품id
    private final String productUrl;   // 상품url
    private final String imageUrl;     // 상품이미지url

    public CreatePostDTO(Long writer, String productName, String content, Long productId, String productUrl, String imageUrl) {
        this.writer = writer;
        this.productName = productName;
        this.content = content;
        this.productId = productId;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
    }

}
