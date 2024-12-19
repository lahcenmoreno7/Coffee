package com.coffee.assessment.configuration;

import com.coffee.assessment.model.Order;

import com.coffee.assessment.repository.OrderRepository;
import com.coffee.assessment.utils.CoffeeConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class ordersDataLoader {

    @Bean
    public CommandLineRunner loadOrdersData(OrderRepository orderRepository) {
        return  args ->{
            ObjectMapper objectMapper = new ObjectMapper();
            try ( InputStream inputStream = getClass().getResourceAsStream(CoffeeConstant.ORDER_FILE)){
                List<Order> orders = objectMapper.readValue(inputStream, new TypeReference<>() { });
                orderRepository.saveAll(orders);

            }catch( Exception ex) {
                throw new RuntimeException("Failed to save orders data.", ex);
            }


        };

    }


}
