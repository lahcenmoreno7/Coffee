package com.coffee.assessment.configuration;

import com.coffee.assessment.model.Payment;
import com.coffee.assessment.repository.PaymentRepository;
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
public class PaymentsDataLoader {

    private static final Logger logger = LoggerFactory.getLogger(PaymentsDataLoader.class);

   @Bean
    public CommandLineRunner loadPaymentsData(PaymentRepository paymentRepository) {
        return  args ->{
            ObjectMapper objectMapper = new ObjectMapper();
            logger.info("Loading payments data to database");
            try (InputStream inputStream = getClass().getResourceAsStream(CoffeeConstant.PAYMENT_FILE)){
                List<Payment> payments = objectMapper.readValue(inputStream, new TypeReference<>() { });
                paymentRepository.saveAll(payments);

            }catch( Exception ex) {
                throw new RuntimeException("Failed to save payments data.", ex);
            }


        };

    }


}
