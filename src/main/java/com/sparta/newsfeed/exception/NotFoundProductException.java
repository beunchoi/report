package com.sparta.newsfeed.exception;

public class NotFoundProductException extends RuntimeException{
    public NotFoundProductException(String message) {
        super(message);
    }
}
