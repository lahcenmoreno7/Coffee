package com.coffee.assessment.configuration;

import com.coffee.assessment.model.Product;
import com.coffee.assessment.repository.ProductRepository;
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
public class ProductsDataLoader {

    private static final Logger logger = LoggerFactory.getLogger(ProductsDataLoader.class);

    @Bean
    public CommandLineRunner loadProductsData(ProductRepository productRepository) {
        return  args ->{
            ObjectMapper objectMapper = new ObjectMapper();
            logger.info("Loading products data to database");
            try (InputStream inputStream = getClass().getResourceAsStream(CoffeeConstant.PRODUCT_FILE)){
                List<Product> products = objectMapper.readValue(inputStream, new TypeReference<>() { });
                productRepository.saveAll(products);

            }catch( Exception ex) {
                throw new RuntimeException("Failed to save products data.", ex);
            }


        };

    }


}
