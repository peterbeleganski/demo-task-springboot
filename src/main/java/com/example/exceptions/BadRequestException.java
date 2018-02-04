package com.example.exceptions;

public class BadRequestException extends RuntimeException {
    private long resourceId;

    public BadRequestException(long resourceId, String message) {
        super(message);
        this.resourceId = resourceId;
    }
}
