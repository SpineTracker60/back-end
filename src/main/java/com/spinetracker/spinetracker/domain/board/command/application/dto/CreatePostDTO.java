package com.spinetracker.spinetracker.domain.board.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreatePostDTO {

    @Schema(type = "Long", example = "1", description = "글 작성자 번호 입니다.")
    private Long writer;

    @Schema(type = "String", example = "상품 이름", description = "상품 이름 입니다.")
    @JsonProperty("product_name")
    private String productName;

    @Schema(type = "String", example = "게시글 내용", description = "게시글 본문 내용 입니다.")
    private String content;

    @Schema(type = "Long", example = "1", description = "상품 번호 입니다.")
    @JsonProperty("product_id")
    private Long productId;

    @Schema(type = "String", example = "상품 URL", description = "상품 URL 입니다.")
    @JsonProperty("product_url")
    private String productUrl;

    @Schema(type = "String", example = "이미지 URL", description = "이미지 URL 입니다.")
    @JsonProperty("image_url")
    private String imageUrl;

    public CreatePostDTO() {}

    public CreatePostDTO(Long writer, String productName, String content, Long productId, String productUrl, String imageUrl) {
        this.writer = writer;
        this.productName = productName;
        this.content = content;
        this.productId = productId;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
    }

}
