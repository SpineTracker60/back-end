package com.spinetracker.spinetracker.domain.board.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductUrlResponseDTO {

    @Schema(type = "String", example = "상품 이름", description = "상품 이름 입니다.")
    @JsonProperty("product_name")
    private String productName;

    @Schema(type = "Long", example = "1", description = "상품 번호 입니다.")
    @JsonProperty("product_id")
    private Long productId;

    @Schema(type = "String", example = "상품 URL", description = "상품 URL 입니다.")
    @JsonProperty("product_url")
    private String productUrl;

    @Schema(type = "String", example = "이미지 URL", description = "이미지 URL 입니다.")
    @JsonProperty("image_url")
    private String imageUrl;


    public ProductUrlResponseDTO() {}

    public ProductUrlResponseDTO(String productName, Long productId, String productUrl, String imageUrl) {
        this.productName = productName;
        this.productId = productId;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
    }
}
