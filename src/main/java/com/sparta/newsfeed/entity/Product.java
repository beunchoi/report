package com.sparta.newsfeed.entity;


import com.sparta.newsfeed.dto.ProductRequestDto;
import com.sparta.newsfeed.security.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor
public class Product extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String productInfo;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String imageUrl;

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setProduct(this);
    }

    public Product(ProductRequestDto requestDto, UserDetailsImpl userDetails, String imageUrl) {
        this.user = userDetails.getUser();
        this.username = userDetails.getUsername();
        this.category = CategoryEnum.valueOf(requestDto.getCategory());
        this.title = requestDto.getTitle();
        this.productInfo = requestDto.getProductInfo();
        this.price = requestDto.getPrice();
        this.imageUrl = imageUrl;
    }

    public void update(ProductRequestDto requestDto, String imageUrl) {
        this.category = CategoryEnum.valueOf(requestDto.getCategory());
        this.title = requestDto.getTitle();
        this.productInfo = requestDto.getProductInfo();
        this.price = requestDto.getPrice();
        this.imageUrl = imageUrl;
    }
}
