package com.spinetracker.spinetracker.domain.board.command.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductUrlRequestDTO {

    @JsonProperty("product_url")
    private String productUrl;

    public ProductUrlRequestDTO() {}

    public ProductUrlRequestDTO(String productUrl) {
        this.productUrl = productUrl;
    }
}
