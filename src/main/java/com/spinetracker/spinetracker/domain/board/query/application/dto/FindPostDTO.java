package com.spinetracker.spinetracker.domain.board.query.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class FindPostDTO {
    private Long id;        // 게시판 번호
    private Long writerId;
    private String content;
    private Long productId;

    public FindPostDTO() {}

    public FindPostDTO(Long id, Long writerId, String content, Long productId) {
        this.id = id;
        this.writerId = getWriterId();
        this.content = content;
        this.productId = productId;
    }
}
