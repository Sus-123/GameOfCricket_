package com.company.Exception;

import org.springframework.http.HttpStatus;

import java.util.Date;


public class GameExceptions extends RuntimeException {

    private Date timestamp;
    private String message;
    private HttpStatus httpStatus;


    public GameExceptions(String message, HttpStatus httpStatus) {
        super();
        this.timestamp = new Date();
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


}
