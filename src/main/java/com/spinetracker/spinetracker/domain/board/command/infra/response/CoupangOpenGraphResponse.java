package com.spinetracker.spinetracker.domain.board.command.infra.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CoupangOpenGraphResponse {

    private String title;
    private String description;
    private String image;
    private String url;
    private Long productId;

    public CoupangOpenGraphResponse() {}

    public CoupangOpenGraphResponse(String title, String description, String image, String url, Long productId) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.url = url;
        this.productId = productId;
    }
}
