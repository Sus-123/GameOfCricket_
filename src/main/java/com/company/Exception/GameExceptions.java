package com.company.Exception;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.util.Date;


@Data
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

}
