package com.coffee.assessment.controller;


import com.coffee.assessment.dto.AmountOwesDTO;
import com.coffee.assessment.exception.CoffeeException;
import com.coffee.assessment.response.CoffeeResponse;
import com.coffee.assessment.service.CoffeeService;
import com.coffee.assessment.utils.CoffeeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/payments")
public class CoffeeController {


    private static final Logger logger = LoggerFactory.getLogger(CoffeeController.class);

    private final CoffeeService coffeeService;


    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }



    @GetMapping("/amount-paid/{username}")
    public ResponseEntity<CoffeeResponse> getAmountPaid (@PathVariable String username){

        logger.info("Received request for amount paid with parameter: {}", username);

        try {
            CoffeeResponse coffeeResponse = new CoffeeResponse();
            double amountPaid = coffeeService.amountPaid(username);
            coffeeResponse.setUser(username);
            coffeeResponse.setTotalPaid(amountPaid);
            return ResponseEntity.ok(coffeeResponse);

        } catch (CoffeeException e) {
            throw new CoffeeException(CoffeeConstant.ERROR_USER_NOT_FOUND);
        }
    }


    @GetMapping("/amount-owed")
    public ResponseEntity<CoffeeResponse[]> getAmountStillOwes () {

        logger.info("Received request for amount owed}");

        try {
            Map<String, Double> amountOwes = coffeeService.amountStillOwes();

            List<CoffeeResponse> coffeeResponse = amountOwes.entrySet().stream()
            .map(entry -> new CoffeeResponse(entry.getKey(), CoffeeConstant.PRICE_NULL, entry.getValue()))
            .toList();

            CoffeeResponse[] coffeeResponseList = coffeeResponse.toArray(new CoffeeResponse[0]);
            return ResponseEntity.ok(coffeeResponseList);
        } catch (CoffeeException e) {
                 throw new CoffeeException(HttpStatus.BAD_REQUEST);
        }
    }

}
