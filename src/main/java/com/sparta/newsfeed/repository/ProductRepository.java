package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.entity.CategoryEnum;
import com.sparta.newsfeed.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findProductByProductId(Long productId);

    List<Product> findAllByOrderByModifiedAtDesc();

    List<Product> findByTitleContaining(String param);

    Page<Product> findAllByCategory(CategoryEnum category, Pageable pageable);
}

