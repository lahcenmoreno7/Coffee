package com.coffee.assessment.model;

import com.coffee.assessment.configuration.PricesConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String drink_name;

    @Convert(converter = PricesConverter.class)
    private Map<String, Double> prices;

}


