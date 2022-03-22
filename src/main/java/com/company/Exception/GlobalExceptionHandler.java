package com.company.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    //@ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler(InvalidInputException.class)
//    public ResponseEntity<?> handleInvalidInputException (InvalidInputException exception, WebRequest request) {
//        GameExceptions errorDetails = new GameExceptions( exception.getMessage(), exception.);
//        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
//    }


    @ExceptionHandler(GameExceptions.class)
    public ResponseEntity<?> gameExceptionHandler(GameExceptions exception, WebRequest request) {
        GameExceptions errorDetails = new GameExceptions(exception.getMessage(),exception.getHttpStatus());
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> generalException(Exception exception, WebRequest request) {
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
