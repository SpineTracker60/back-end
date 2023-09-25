package com.spinetracker.spinetracker.domain.board.query.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FindProductDTO {

    @Schema(type = "Long", example = "1", description = "상품 번호 입니다.")
    @JsonProperty("product_id")
    private Long productId;

    @Schema(type = "String", example = "상품 URL", description = "상품 URL 입니다.")
    @JsonProperty("product_url")
    private String productUrl;

    @Schema(type = "String", example = "이미지 URL", description = "이미지 URL 입니다.")
    @JsonProperty("image_url")
    private String imageUrl;

    @Schema(type = "String", example = "상품 이름", description = "상품 이름 입니다.")
    @JsonProperty("product_name")
    private String productName;

    public FindProductDTO(Long productId, String productUrl, String imageUrl, String productName) {
        this.productId = productId;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
        this.productName = productName;
    }
}
