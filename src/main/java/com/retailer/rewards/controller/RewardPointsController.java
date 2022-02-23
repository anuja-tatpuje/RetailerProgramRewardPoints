package com.retailer.rewards.controller;

import com.retailer.rewards.component.Customer;
import com.retailer.rewards.service.RewardPointsCalculatorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rewards")
public class RewardPointsController {

    private Map<String, List<Customer>> customers;
    private RewardPointsCalculatorService rewardPointsCalculatorService;


    public RewardPointsController(RewardPointsCalculatorService rewardPointsCalculatorService) {
        this.rewardPointsCalculatorService = rewardPointsCalculatorService;
    }

    @GetMapping(value = "/calculate-rewards/{customerId}/{amount}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> calculateRewardPoints(@PathVariable String customerId,@PathVariable Double amount) {
        customers = rewardPointsCalculatorService.calculateRewardPointsForCustomerId(customerId,amount);
        double calculatedRewardPoints = 0;
        calculatedRewardPoints =  customers.get(customerId).stream().mapToDouble(value -> value.getRewardPoints()).sum();
        return ResponseEntity.ok(calculatedRewardPoints);
    }
}
