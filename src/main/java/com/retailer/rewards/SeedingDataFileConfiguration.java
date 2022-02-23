package com.retailer.rewards;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.retailer.rewards.component.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan
public class SeedingDataFileConfiguration {
    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }
    @Bean
    public Map<String, List<Customer>> createListOfCustomers() {
        final Map<String, List<Customer>> readings = new HashMap<>();
        return readings;
    }

    @Bean
    @Primary
    public Customer getCustomer() {
        return new Customer();
    }
}
