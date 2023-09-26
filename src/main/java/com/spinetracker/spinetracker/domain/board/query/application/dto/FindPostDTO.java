package com.spinetracker.spinetracker.domain.board.query.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class FindPostDTO {

    @Schema(type = "long", example = "1", description = "게시판 번호 입니다.")
    private Long id;

    @Schema(type = "long", example = "1", description = "사용자 아이디 입니다.")
    @JsonProperty("writer_id")
    private Long writerId;

    @Schema(type = "String", example = "게시글 내용", description = "게시글 본문 내용 입니다.")
    private String content;

    @Schema(type = "Long", example = "1", description = "상품 번호 입니다.")
    @JsonProperty("product_id")
    private Long productId;

    public FindPostDTO() {}

    public FindPostDTO(Long id, Long writerId, String content, Long productId) {
        this.id = id;
        this.writerId = getWriterId();
        this.content = content;
        this.productId = productId;
    }
}
