package com.bikram.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){

        // getting the message from 'ResourceNotFundException' -> 'ex'
        String message = ex.getMessage();

        // building 'ApiResponse' using 'builer' method
        ApiResponse response = ApiResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND).build();

        // return statement (returning 'response' with status-code)
        return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);

    }
}
