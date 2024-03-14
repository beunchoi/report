package com.sparta.newsfeed.cond;

import com.sparta.newsfeed.entity.CategoryEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSearchCond {
    private CategoryEnum category;
}
