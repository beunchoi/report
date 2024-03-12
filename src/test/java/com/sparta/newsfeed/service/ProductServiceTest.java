package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.PageDto;
import com.sparta.newsfeed.dto.ProductResponseDto;
import com.sparta.newsfeed.entity.CategoryEnum;
import com.sparta.newsfeed.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Test
    void test() {
        //given
        Integer currentPage = 3;
        Integer size = 3;
        String sortBy = "category";
        PageDto pageDto = new PageDto(currentPage,size,sortBy);
        //when
        List<ProductResponseDto> responseDto = productService.getProductByCategory(pageDto);
        //then
        assertEquals(CategoryEnum.Electronics,responseDto.get(0).getCategory());
    }

}