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

    @Schema(type = "String", example = "게시글 내용", description = "게시글 본문 내용 입니다.")
    private String content;

    @Schema(type = "Long", example = "1", description = "상품 번호 입니다.")
    @JsonProperty("product_id")
    private Long productId;

    public CreatePostDTO() {}

    public CreatePostDTO(Long writer, String content, Long productId) {
        this.writer = writer;
        this.content = content;
        this.productId = productId;
    }

}
