package com.spinetracker.spinetracker.domain.board.query.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FindBoardDTO {

    @Schema(type = "Long", example = "1", description = "게시판 번호 입니다.")
    @JsonProperty("board_id")
    private final Long boardId;

    @Schema(type = "Long", example = "1", description = "작성자 번호 입니다.")
    @JsonProperty("writer_id")
    private final Long writerId;

    @Schema(type = "String", example = "홍길동", description = "작성자 이름 입니다.")
    @JsonProperty("writer_name")
    private final String writerName;

    @Schema(type = "String", example = "프로필 URL", description = "작성자 프로필 이미지 입니다.")
    @JsonProperty("profile_image")
    private final String profileImage;

    @Schema(type = "String", example = "게시글 내용", description = "게시글 본문 내용 입니다.")
    private final String content;

    @Schema(type = "String", example = "상품 이름", description = "상품 이름 입니다.")
    @JsonProperty("product_name")
    private final String productName;

    @Schema(type = "String", example = "상품 URL", description = "상품 URL 입니다.")
    @JsonProperty("product_url")
    private final String productUrl;

    @Schema(type = "String", example = "이미지 URL", description = "이미지 URL 입니다.")
    @JsonProperty("image_url")
    private final String imageUrl;

    public FindBoardDTO(Long boardId, Long writerId, String writerName, String profileImage, String content, String productName, String productUrl, String imageUrl) {
        this.boardId = boardId;
        this.writerId = writerId;
        this.writerName = writerName;
        this.profileImage = profileImage;
        this.content = content;
        this.productName = productName;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
    }
}
