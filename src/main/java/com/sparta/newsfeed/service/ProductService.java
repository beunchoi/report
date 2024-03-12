package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.PageDto;
import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.dto.ProductResponseDto;
import com.sparta.newsfeed.entity.CategoryEnum;
import com.sparta.newsfeed.entity.Notification;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.Wish;
import com.sparta.newsfeed.exception.NotFoundProductException;
import com.sparta.newsfeed.repository.NotificationRepository;
import com.sparta.newsfeed.repository.ProductRepository;
import com.sparta.newsfeed.repository.WishRepository;
import com.sparta.newsfeed.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto requestDto, UserDetailsImpl userDetails);

    List<ProductResponseDto> getProduct();

    List<ProductResponseDto> getProductByCategory(PageDto pageDto);

    List<ProductResponseDto> getProductById(Long productId);

    List<ProductResponseDto> searchProduct(String param);

    Long updateProduct(Long productId, ProductRequestDto requestDto);

    Long deleteProduct(Long productId);
}
