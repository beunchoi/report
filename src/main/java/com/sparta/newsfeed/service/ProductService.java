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

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UploadService uploadService;
    private final WishRepository wishRepository;
    private final NotificationRepository notificationRepository;
    private final MessageSource messageSource;

    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String imageUrl = uploadService.uploadImageAndGetUrl(requestDto.getImage());
        Product product = productRepository.save(new Product(requestDto, userDetails, imageUrl));
        return new ProductResponseDto(product);
    }

    public List<ProductResponseDto> getProduct() {
        return productRepository.findAllByOrderByModifiedAtDesc().stream().map(ProductResponseDto::new).toList();
    }
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getProductByCategory(PageDto pageDto) {
        Page<Product> productByCategory = productRepository.findAllByCategory(CategoryEnum.Electronics,pageDto.toPageable());
        List<ProductResponseDto> responseDto = new ArrayList<>();

        for (Product p : productByCategory) {
            responseDto.add(new ProductResponseDto(p.getProductId(),p.getUsername(),p.getCategory(),p.getTitle(),p.getProductInfo(),p.getPrice(),p.getCreatedAt(),p.getModifiedAt(),p.getImageUrl()));
        }
        return responseDto;
    }

    public List<ProductResponseDto> getProductById(Long productId) {
        return productRepository.findProductByProductId(productId).stream().map(ProductResponseDto::new).toList();
    }

    public List<ProductResponseDto> searchProduct(String param) {
        return productRepository.findByTitleContaining(param).stream().map(ProductResponseDto::new).toList();
    }
    @Transactional
    public Long updateProduct(Long productId, ProductRequestDto requestDto) {
        Product product = findProduct(productId);
        String imageUrl = uploadService.uploadImageAndGetUrl(requestDto.getImage());
        int oldPrice = product.getPrice();
        int newPrice = requestDto.getPrice();
        product.update(requestDto, imageUrl);
        if(oldPrice != newPrice){
            notifyPriceChange(product, oldPrice, newPrice);
        }
        return productId;
    }

    @Transactional
    public Long deleteProduct(Long productId) {
        Product product = findProduct(productId);

        productRepository.delete(product);

        return productId;
    }

    private Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() ->
                new NotFoundProductException(
                        messageSource.getMessage(
                                "not.found.product",
                                null,
                                "Not Found",
                                Locale.getDefault()
                        )
                ));
    }

    private void notifyPriceChange(Product product, int oldPrice, int newPrice) {
        List<Wish> wishList = wishRepository.findByProductProductId(product.getProductId());
        for (Wish wish : wishList) {
            Notification notification = new Notification();
            notification.setUser(wish.getUser());
            notification.setMessage("관심 상품으로 등록하신 [" + product.getTitle() + "] 의 가격이 ["
                    + oldPrice + "] 에서 [" + newPrice + "] 으로 변동되었습니다!");
            notificationRepository.save(notification);
        }
    }
}
