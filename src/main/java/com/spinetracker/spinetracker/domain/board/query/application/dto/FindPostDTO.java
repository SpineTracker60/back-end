package com.spinetracker.spinetracker.domain.board.query.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class FindPostDTO {
    private Long writerId;
    private String content;
    private Long productId;

    public FindPostDTO() {}

    public FindPostDTO(String writerId, String content, Long productId) {
        this.writerId = getWriterId();
        this.content = content;
        this.productId = productId;
    }
}
