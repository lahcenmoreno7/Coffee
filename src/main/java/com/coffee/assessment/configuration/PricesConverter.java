package com.coffee.assessment.configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PricesConverter implements AttributeConverter<Map<String, Double>, String> {

    private static final Logger logger = LoggerFactory.getLogger(PricesConverter.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Double> attribute) {
        logger.info("Convert to database column");
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert map to JSON string.", e);
        }
    }

    @Override
    public Map<String, Double> convertToEntityAttribute(String dbData) {
        logger.info("Convert to entity attribute");
        try {
            return objectMapper.readValue(dbData, new TypeReference<>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON string to map.", e);
        }
    }
}
