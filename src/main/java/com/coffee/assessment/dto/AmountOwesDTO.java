package com.coffee.assessment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.ConstructorParameters;
import java.util.Map;

@Data
@AllArgsConstructor
public class AmountOwesDTO {

    private String user;
    private String drink;
    private String size;
    private Map<String, Double> prices;


}
