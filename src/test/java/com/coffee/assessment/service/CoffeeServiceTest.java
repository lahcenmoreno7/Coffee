package com.coffee.assessment.service;

import com.coffee.assessment.repository.OrderRepository;
import com.coffee.assessment.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CoffeeServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private CoffeeService coffeeService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void amoundPaidTest (){
        String username = "paco";
        double expectedAmountPaid = 30.0;

        when(paymentRepository.findTotalPaidByUser(username)).thenReturn(expectedAmountPaid);

        double actualAmountPaid = coffeeService.amountPaid(username);

        assertEquals(expectedAmountPaid, actualAmountPaid);

        verify(paymentRepository, times(1)).findTotalPaidByUser(username);
    }

    @Test
    void AmountStillOwesTest() {

        List<Map<String, Object>> orderDetails = new ArrayList<>();
        Map<String, Object> order1 = new HashMap<>();
        order1.put("user", "paco");
        order1.put("size", "large");
        order1.put("prices", Map.of("small", 5.0, "medium", 7.0, "large", 10.0));
        orderDetails.add(order1);


        List<Map<String, Object>> payments = new ArrayList<>();
        Map<String, Object> payment1 = new HashMap<>();
        payment1.put("user", "paco");
        payment1.put("amount", 1.0);
        payments.add(payment1);

        when(orderRepository.findTotalOwesByUser()).thenReturn(orderDetails);
        when(paymentRepository.findPayments()).thenReturn(payments);

        Map<String, Double> expectedOwes = new HashMap<>();
        expectedOwes.put("paco", 9.0);

        Map<String, Double> actualOwes = coffeeService.amountStillOwes();

        assertEquals(expectedOwes, actualOwes);

        verify(orderRepository, times(1)).findTotalOwesByUser();
        verify(paymentRepository, times(1)).findPayments();
    }


}
