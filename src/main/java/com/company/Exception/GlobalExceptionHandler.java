package com.company.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(GameExceptions.class)
    public ResponseEntity<?> gameExceptionHandler(GameExceptions exception) {
        GameExceptions errorDetails = new GameExceptions(exception.getMessage(),exception.getHttpStatus());
        return new ResponseEntity(errorDetails, exception.getHttpStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> generalException(Exception exception) {
        GameExceptions errorDetails = new GameExceptions("Internal Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
