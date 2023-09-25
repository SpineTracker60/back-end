package com.spinetracker.spinetracker.domain.board.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class CreateProductDTO {
    private final Long productId;
    private final String productUrl;
    private final String imageUrl;
    private final String productName;

    public CreateProductDTO(Long productId, String productUrl, String imageUrl, String productName) {
        this.productId = productId;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
        this.productName = productName;
    }
}
