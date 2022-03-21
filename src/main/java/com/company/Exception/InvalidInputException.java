package com.company.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid Input")
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }


}
