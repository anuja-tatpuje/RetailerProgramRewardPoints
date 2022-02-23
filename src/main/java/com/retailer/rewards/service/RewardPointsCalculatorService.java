package com.retailer.rewards.service;

import com.retailer.rewards.component.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RewardPointsCalculatorService {

    @Autowired
    ApplicationContext applicationContext;
    private Map<String, List<Customer>> customers;

    public RewardPointsCalculatorService(Map<String, List<Customer>> customers) {
        this.customers = customers;
    }

    public Map<String, List<Customer>> calculateRewardPointsForCustomerId(String customerId, Double amount) {
        double calculatedRewardPoints = 0;
        LocalDate currentDate = LocalDate.now();
        // Get month from date
        Month month = currentDate.getMonth();
        if(customers.containsKey(customerId)) {
           List<Customer> customerList =  customers.get(customerId);
           calculatedRewardPoints = calculateRewardPoints(amount);
            Customer customer = applicationContext.getBean(Customer.class);
            customer.setCustomerId(customerId);
            customer.setRewardPoints(calculatedRewardPoints);
            customer.setMonths(month);
            customerList.add(customer);
        } else {
            Customer customer = applicationContext.getBean(Customer.class);
            customer.setCustomerId(customerId);
            calculatedRewardPoints = calculateRewardPoints(amount);
            customer.setRewardPoints(calculatedRewardPoints);
            customer.setMonths(month);
            List<Customer> customerList = new ArrayList<>();
            customerList.add(customer);
            customers.put(customerId,customerList);
        }
        return customers;
    }
    private Double calculateRewardPoints(Double amount) {
        double calcRewardPoints = 0;
        if(amount > 100) {
            double temp100A = amount;
            double diff = temp100A - 100;
            calcRewardPoints = calcRewardPoints + (diff * 2);
        }
        if(amount > 50) {
            double temp50A = amount;
            int start = 0;
            while(temp50A >= 50) {
                temp50A = temp50A - 50;
                if(start > 0) {
                    calcRewardPoints  = calcRewardPoints + (50 * 1);
                }
                start++;
            }
        }
        return calcRewardPoints;
    }

}
