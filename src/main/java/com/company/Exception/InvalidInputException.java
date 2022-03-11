package com.company.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid Input")
public class InvalidInputException extends RuntimeException {

   // private static final long serialVersionUID = 1L;

    public InvalidInputException(String message) {
        super(message);
    }


}
