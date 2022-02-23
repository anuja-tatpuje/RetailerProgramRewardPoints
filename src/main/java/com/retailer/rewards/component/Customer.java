package com.retailer.rewards.component;


import org.springframework.stereotype.Component;

import java.time.Month;

@Component
public class Customer {
    private String customerId;
    private double rewardPoints;
    private Month months;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Month getMonths() {
        return months;
    }

    public void setMonths(Month months) {
        this.months = months;
    }
}
