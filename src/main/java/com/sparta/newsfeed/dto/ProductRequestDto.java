package com.sparta.newsfeed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequestDto {
    private String category;
    private String title;
    private String productInfo;
    private int price;
    private MultipartFile image;
}
