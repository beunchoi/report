package com.sparta.newsfeed.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.newsfeed.cond.ProductSearchCond;
import com.sparta.newsfeed.entity.CategoryEnum;
import com.sparta.newsfeed.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static com.sparta.newsfeed.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryQueryImpl implements ProductRepositoryQuery{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Product> searchByCategory(ProductSearchCond cond, Pageable pageable) {
        var query = query(product,cond)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        var products = query.fetch();
        long totalSize = countQuery(cond).fetch().get(0);

        return PageableExecutionUtils.getPage(products,pageable,() -> totalSize);
    }

    private <T> JPAQuery<T> query(Expression<T> expr, ProductSearchCond cond) {
        return jpaQueryFactory.select(expr)
                .from(product)
                .leftJoin(product.user).fetchJoin()
                .where(
                       productCategoryEq(cond.getCategory())
                );
    }

    private JPAQuery<Long> countQuery(ProductSearchCond cond) {
        return jpaQueryFactory.select(Wildcard.count)
                .from(product)
                .where(
                        productCategoryEq(cond.getCategory())
                );
    }

    private BooleanExpression productCategoryEq(CategoryEnum categoryEnum) {
        return Objects.nonNull(categoryEnum) ? product.category.eq(categoryEnum) : null;
    }
}
