package com.tui.api.rest.exception.advise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.tui.api.rest.exception.error.ErrorResponse;
import com.tui.application.service.exception.QuoteNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuoteNotFoundException.class)
    public ResponseEntity<Object> handleQuoteNotFoundException(QuoteNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }   

    // Generic exception handler for all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

 
}

