package com.retailer.rewards.controller;

import com.retailer.rewards.component.Customer;
import com.retailer.rewards.service.RewardPointsCalculatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.*;

import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

public class TestRewardPointsController {
    private RewardPointsCalculatorService rewardPointsCalculatorService;
    private RewardPointsController rewardPointsController;
    @Mock
    RewardPointsCalculatorService spyRewardPointsCalculatorService;
    @InjectMocks
    RewardPointsController spyRewardPointsController;

    @Before
    public void setup() {
        this.rewardPointsCalculatorService = new RewardPointsCalculatorService(Collections.EMPTY_MAP);
        this.rewardPointsController = new RewardPointsController(rewardPointsCalculatorService);
        spyRewardPointsController = Mockito.spy(rewardPointsController);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateRewardPoints() {
        Map<String, List<Customer>> map = new HashMap<>();
        Customer customer = new Customer();
        customer.setRewardPoints(90.0);
        List<Customer> list = new ArrayList<>();
        list.add(customer);
        map.put("fgi",list);
        doReturn(map).when(spyRewardPointsCalculatorService).calculateRewardPointsForCustomerId(anyString(),anyDouble());
        Assert.assertEquals(HttpStatus.OK,spyRewardPointsController.calculateRewardPoints("fgi",90.0).getStatusCode());
    }
}
