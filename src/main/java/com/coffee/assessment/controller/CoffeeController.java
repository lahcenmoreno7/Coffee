package com.coffee.assessment.controller;


import com.coffee.assessment.dto.AmountOwesDTO;
import com.coffee.assessment.response.CoffeeResponse;
import com.coffee.assessment.service.CoffeeService;
import com.coffee.assessment.utils.CoffeeConstant;
import org.springframework.beans.factory.annotation.Autowired;
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


    private final CoffeeService coffeeService;


    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }



    @GetMapping("/amount-paid/{username}")
    public ResponseEntity<CoffeeResponse> getAmountPaid (@PathVariable String username){

        CoffeeResponse coffeeResponse = new CoffeeResponse();
        double amountPaid = coffeeService.amountPaid(username);
        coffeeResponse.setUser(username);
        coffeeResponse.setTotalPaid(amountPaid);

        return ResponseEntity.ok(coffeeResponse);
    }


    @GetMapping("/amount-owed")
    public ResponseEntity<CoffeeResponse[]> getAmountStillOwes () {
            Map<String, Double> amountOwes = coffeeService.amountStillOwes();

            List<CoffeeResponse> coffeeResponse = amountOwes.entrySet().stream()
            .map(entry -> new CoffeeResponse(entry.getKey(), CoffeeConstant.PRICE_NULL, entry.getValue()))
            .toList();

            CoffeeResponse[] coffeeResponseList = coffeeResponse.toArray(new CoffeeResponse[0]);

            return ResponseEntity.ok(coffeeResponseList);

    }

}
