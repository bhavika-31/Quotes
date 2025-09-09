package com.example.demo;

public class RateLimitExcedException extends RuntimeException {
    public RateLimitExcedException(String message) {
        super(message);
    }
}

