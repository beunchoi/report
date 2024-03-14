package com.sparta.newsfeed.repository;

import com.sparta.newsfeed.entity.CategoryEnum;
import com.sparta.newsfeed.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Product.class, idClass = Long.class)
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQuery {

    List<Product> findProductByProductId(Long productId);

    List<Product> findAllByOrderByModifiedAtDesc();

    List<Product> findByTitleContaining(String param);

    Page<Product> findAllByCategory(CategoryEnum category, Pageable pageable);
}

