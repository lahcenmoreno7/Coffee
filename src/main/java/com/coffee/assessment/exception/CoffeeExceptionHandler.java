package com.coffee.assessment.exception;

import com.coffee.assessment.utils.CoffeeConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoffeeExceptionHandler {


    @ExceptionHandler(CoffeeException.class)
    public ResponseEntity<ErrorResponse> handleCoffeeException(CoffeeException ex) {

        ErrorResponse errorResponse = new ErrorResponse(CoffeeConstant.ERROR_USER_NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CoffeeException> handleGenericException(Exception ex) {
        CoffeeException errorResponse = new CoffeeException(CoffeeConstant.ERROR_UNEXPECTED);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
