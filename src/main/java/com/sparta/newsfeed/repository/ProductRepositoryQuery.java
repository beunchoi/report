package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.cond.ProductSearchCond;
import com.sparta.newsfeed.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductRepositoryQuery {
    Page<Product> searchByCategory(ProductSearchCond cond, Pageable pageable);
}
