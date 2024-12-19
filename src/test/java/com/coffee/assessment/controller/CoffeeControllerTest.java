package com.coffee.assessment.controller;


import com.coffee.assessment.response.CoffeeResponse;
import com.coffee.assessment.service.CoffeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CoffeeControllerTest {

    @Mock
    private CoffeeService coffeeService;

    @InjectMocks
    private CoffeeController coffeeController;


    @Test
    void getAmountPaidTest(){

        String username = "user 1";
        double amountPaidMocked = 10.0;

        when(coffeeService.amountPaid(username)).thenReturn(amountPaidMocked);
        ResponseEntity<CoffeeResponse> response = coffeeController.getAmountPaid(username);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(username, response.getBody().getUser());
        assertEquals(amountPaidMocked, response.getBody().getTotalPaid());

    }

    @Test
    void getAmountStillOwesTest() {

        Map<String, Double> mockOwesMap = new HashMap<>();
        mockOwesMap.put("user1", 20.0);
        mockOwesMap.put("user2", 35.0);

        when(coffeeService.amountStillOwes()).thenReturn(mockOwesMap);

        // Act
        ResponseEntity<CoffeeResponse[]> response = coffeeController.getAmountStillOwes();

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);

        CoffeeResponse[] responses = response.getBody();

        assertEquals("user1", responses[0].getUser());
        assertEquals(20.0, responses[0].getTotalOwes());
        assertEquals("user2", responses[1].getUser());
        assertEquals(35.0, responses[1].getTotalOwes());

        verify(coffeeService, times(1)).amountStillOwes();
    }
}
