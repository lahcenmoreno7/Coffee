package com.coffee.assessment.service;

import com.coffee.assessment.dto.AmountOwesDTO;
import com.coffee.assessment.exception.CoffeeException;
import com.coffee.assessment.repository.OrderRepository;
import com.coffee.assessment.repository.PaymentRepository;
import com.coffee.assessment.repository.ProductRepository;
import com.coffee.assessment.utils.CoffeeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoffeeService {

    private static final Logger logger = LoggerFactory.getLogger(CoffeeService.class);

   private final OrderRepository orderRepository;
   private final PaymentRepository paymentRepository;


    public CoffeeService(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }



    public double amountPaid(String username){
        logger.info("processing amount paid");

        Double totalPaid = paymentRepository.findTotalPaidByUser(username);

        if (totalPaid == null) {
           throw new CoffeeException(CoffeeConstant.ERROR_USER_NOT_FOUND);
        }

        return totalPaid;
    }


    public Map<String, Double> amountStillOwes() {

        logger.info("processing amount still owes");

        Map<String, Double> owesMapUsers = new HashMap<>();

        // Get orders and prices for all users
        List<Map<String, Object>> orderDetails = orderRepository.findTotalOwesByUser();

        for (Map<String, Object> orderDetail : orderDetails) {
            String user = (String) orderDetail.get("user");
            String size = (String) orderDetail.get("size");
            Map<String, Double> prices = (Map<String, Double>) orderDetail.get("prices");

            // Look up the price for the given size
            double price = CoffeeConstant.PRICE_NULL;
            if (prices != null && prices.containsKey(size)) {
                price = prices.get(size);
            }

            // Accumulate the total owed by the user
            owesMapUsers.merge(user, price, Double::sum);
        }

        // Process payments
        List<Map<String, Object>> payments = paymentRepository.findPayments();
        for (Map<String, Object> payment : payments) {
            String user = (String) payment.get("user");
            double amountPaid = (Double) payment.get("amount");

            // Subtract the payments from the owed amount
            owesMapUsers.merge(user, -amountPaid, Double::sum);
        }

        return owesMapUsers;
    }


}



