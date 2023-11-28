package com.hakan.exception;

import lombok.Getter;

@Getter
public class BlogException extends RuntimeException{

    private final ErrorType errorType;

    public BlogException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public BlogException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

}
