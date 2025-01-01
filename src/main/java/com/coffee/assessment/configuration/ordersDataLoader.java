package com.coffee.assessment.configuration;

import com.coffee.assessment.model.Order;

import com.coffee.assessment.repository.OrderRepository;
import com.coffee.assessment.service.CoffeeService;
import com.coffee.assessment.utils.CoffeeConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class ordersDataLoader {

    private static final Logger logger = LoggerFactory.getLogger(ordersDataLoader.class);

    @Bean
    public CommandLineRunner loadOrdersData(OrderRepository orderRepository) {
        return  args ->{
            ObjectMapper objectMapper = new ObjectMapper();
            logger.info("Loading orders data to database");
            try ( InputStream inputStream = getClass().getResourceAsStream(CoffeeConstant.ORDER_FILE)){
                List<Order> orders = objectMapper.readValue(inputStream, new TypeReference<>() { });
                orderRepository.saveAll(orders);

            }catch( Exception ex) {
                throw new RuntimeException("Failed to save orders data.", ex);
            }
        };

    }


}
