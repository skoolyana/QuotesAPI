package com.tui.application.service.exception;

public class QuoteNotFoundException extends RuntimeException {
    public QuoteNotFoundException(String message) {
        super(message);
    }
}
