package com.coffee.assessment.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class CoffeeException extends RuntimeException  {

    private String message;
    private HttpStatus httpStatus;

    public CoffeeException(String message) {
        super(message);
    }

    public CoffeeException(HttpStatus httpStatus) {
        super(String.valueOf(httpStatus));
    }

}
