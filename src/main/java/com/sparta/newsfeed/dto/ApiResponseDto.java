package com.sparta.newsfeed.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiResponseDto {
    private String message;
    private int code;
}

