package com.coffee.assessment.exception;

import org.springframework.http.HttpStatus;

public class CoffeeException extends  RuntimeException{


    public String param;
    public String  message;
    public String username;

    public CoffeeException(String username) {

           super(String.format("'%s' not found ",username));
    }



    public CoffeeException(Throwable cause) {
        super(cause);

    }

    public CoffeeException(HttpStatus status) {
        super(status.toString());
    }


}
