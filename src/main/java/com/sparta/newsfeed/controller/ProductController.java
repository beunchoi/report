package com.sparta.newsfeed.controller;

import com.sparta.newsfeed.dto.PageDto;
import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.dto.ProductResponseDto;
import com.sparta.newsfeed.entity.CategoryEnum;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.security.UserDetailsImpl;
import com.sparta.newsfeed.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product") // JSON 데이터와 이미지를 함께 업로드하기 위해 @ModelAttribute 애너테이션 사용
    public ProductResponseDto createProduct(@ModelAttribute ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createProduct(requestDto, userDetails);
    }

    @GetMapping("/product")
    public List<ProductResponseDto> getProduct() {
        return productService.getProduct();
    }

    @GetMapping("/product/{productId}")
    public List<ProductResponseDto> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/product")
    public Page<Product> searchByCategory(CategoryEnum category, PageDto pageDto) {
        return productService.searchByCategory(category,pageDto);
    }

    @GetMapping("/product/search")
    public List<ProductResponseDto> searchProduct(@RequestParam String param){
        return productService.searchProduct(param);
    }

    @PutMapping("/product/{productId}")
    public Long updateProduct(@PathVariable Long productId, @ModelAttribute ProductRequestDto requestDto) {
        return productService.updateProduct(productId, requestDto);
    }

    @DeleteMapping("/product/{productId}")
    public Long deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }
}
