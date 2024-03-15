package com.sparta.newsfeed.service;

import com.sparta.newsfeed.dto.PageDto;
import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.dto.ProductResponseDto;
import com.sparta.newsfeed.entity.CategoryEnum;
import com.sparta.newsfeed.entity.Product;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.repository.ProductRepository;
import com.sparta.newsfeed.repository.UserRepository;
import com.sparta.newsfeed.security.UserDetailsImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void getProductByCategoryTest1() throws IOException {
        //given
        Integer currentPage = 1;
        Integer size = 10;
        String sortBy = "category";
        PageDto pageDto = new PageDto(currentPage,size,sortBy);

        User user1 = new User("test","test@email.com","1234","testtest");
        userRepository.save(user1);
        UserDetailsImpl userDetails = new UserDetailsImpl(user1);

        MockMultipartFile image = new MockMultipartFile(
                "test",
                "a.png",
                "image/png",
                new FileInputStream(new File("C:/Users/Owner/Pictures/Screenshots/a.png")));
        String imageUrl = "http://localhost:8080/uploads/image";

        Product p1 = new Product(new ProductRequestDto("Electronics","test1","test2",100,image),userDetails,imageUrl);
        productRepository.save(p1);

        //when
        List<ProductResponseDto> responseDto = productService.getProductByCategory(pageDto);

        //then
        assertEquals(CategoryEnum.Electronics,responseDto.get(0).getCategory());
    }

    @Test
    void searchByCategoryTest2() throws IOException {
        // given
        CategoryEnum category = CategoryEnum.Electronics;
        Integer currentPage = 1;
        Integer size = 10;
        String sortBy = "category";
        PageDto pageDto = new PageDto(currentPage,size,sortBy);

        User user1 = new User("test","test@email.com","1234","testtest");
        userRepository.save(user1);
        UserDetailsImpl userDetails = new UserDetailsImpl(user1);

        MockMultipartFile image = new MockMultipartFile(
                "test",
                "a.png",
                "image/png",
                new FileInputStream(new File("C:/Users/Owner/Pictures/Screenshots/a.png")));
        String imageUrl = "http://localhost:8080/uploads/image";

        Product p1 = new Product(new ProductRequestDto("Electronics","test1","test2",100,image),userDetails,imageUrl);
        productRepository.save(p1);

        // when
        Page<Product> selectedProduct = productService.searchByCategory(category,pageDto);

        // then
        assertEquals(1L,selectedProduct.getTotalElements());
    }
}