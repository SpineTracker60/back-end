package com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ProductVO {

    @Column(nullable = false, name = "product_id")
    private String id;

    @Column(nullable = false, name="product_url")
    private String url;

    protected ProductVO() {}

    public ProductVO(String id, String url) {
        this.id = id;
        this.url = url;
    }
}
