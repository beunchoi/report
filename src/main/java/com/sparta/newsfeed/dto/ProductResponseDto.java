package com.sparta.newsfeed.dto;

import com.sparta.newsfeed.entity.CategoryEnum;
import com.sparta.newsfeed.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProductResponseDto {
    private Long productId;
    private String username;
    private CategoryEnum category;
    private String title;
    private String productInfo;
    private int price;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String imageUrl;

    public ProductResponseDto(Product saveProduct) {
        this.productId = saveProduct.getProductId();
        this.username = saveProduct.getUsername();
        this.category = saveProduct.getCategory();
        this.title = saveProduct.getTitle();
        this.productInfo = saveProduct.getProductInfo();
        this.price = saveProduct.getPrice();
        this.createdAt = saveProduct.getCreatedAt();
        this.modifiedAt = saveProduct.getModifiedAt();
        this.imageUrl = saveProduct.getImageUrl();
    }
}
