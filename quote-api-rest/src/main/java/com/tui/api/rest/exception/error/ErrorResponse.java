package com.tui.api.rest.exception.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
	
	 private final int status;
     private final String message;

     public ErrorResponse(HttpStatus status, String message) {
         this.status = status.value();
         this.message = message;
     }

     public int getStatus() {
         return status;
     }

     public String getMessage() {
         return message;
     }

}
