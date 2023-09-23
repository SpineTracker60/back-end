package com.spinetracker.spinetracker.domain.board.command.domain.aggregate.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_TB")
@Getter
public class Product {

    @Id
    @Column(nullable = false, name = "product_id")
    private Long productId;

    @Column(nullable = false, name="product_url")
    private String productUrl;

    @Column(nullable = false, name="image_url")
    private String imageUrl;

    @Column(nullable = false, name="product_name")
    private String productName;

    protected Product() {}

    public Product(Long productId, String productUrl, String imageUrl, String productName) {
        this.productId = productId;
        this.productUrl = productUrl;
        this.imageUrl = imageUrl;
        this.productName = productName;
    }
}
