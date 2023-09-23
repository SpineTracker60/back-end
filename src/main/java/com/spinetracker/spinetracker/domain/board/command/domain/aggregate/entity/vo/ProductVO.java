package com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class ProductVO {

    @Column(nullable = false, name = "product_id")
    private Long id;

    protected ProductVO() {}

    public ProductVO(Long id) {
        this.id = id;
    }
}
