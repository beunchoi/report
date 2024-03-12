package com.sparta.newsfeed.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
public class PageDto {
    @Positive
    private Integer currentPage;
    private Integer size;
    private String sortBy;

    public Pageable toPageable() {
        return PageRequest.of(currentPage-1, size, Sort.by(sortBy).descending());
    }
}
