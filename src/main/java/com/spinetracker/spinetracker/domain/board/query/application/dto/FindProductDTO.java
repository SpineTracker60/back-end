package com.spinetracker.spinetracker.domain.board.query.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FindProductDTO {
    private Long productId;
    private String productUrl;
    private String imageUrl;
    private String productName;

    public FindProductDTO() {}

    public FindProductDTO(Long productId, String productUrl, String imageUrl, String productName) {
        this.productId = productId;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
        this.productName = productName;
    }
}
