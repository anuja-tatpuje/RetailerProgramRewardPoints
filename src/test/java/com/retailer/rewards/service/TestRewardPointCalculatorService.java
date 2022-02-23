package com.retailer.rewards.service;

import com.retailer.rewards.component.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

public class TestRewardPointCalculatorService {
    @Spy
    Map<String, List<Customer>> map;
    @Mock
    ApplicationContext applicationContext;
    @InjectMocks
    RewardPointsCalculatorService rewardPointsCalculatorService;

    @Before
    public void setup() {
        map = new HashMap<>();
        RewardPointsCalculatorService rpcs = new RewardPointsCalculatorService(map);
        rewardPointsCalculatorService = Mockito.spy(rpcs);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCalculateRewardPointsForNewCustomerId() {
        String customerId = "fgi";
        Double amount = 120.0;
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setRewardPoints(90.0);
        customerList.add(customer);
        doReturn(true).when(map).get(customerId);
        doReturn(customerList).when(map).get(customerId);
        Customer c1 = new Customer();
        doReturn(c1).when(applicationContext).getBean(any(Class.class));
        Map<String, List<Customer>> mp = rewardPointsCalculatorService.calculateRewardPointsForCustomerId(customerId,amount);
        Assert.assertEquals(1,mp.size());
    }

    @Test
    public void testCalculateRewardPointsForCustomerId() {
        String customerId = "fgi";
        Double amount = 120.0;
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setRewardPoints(90.0);
        customerList.add(customer);
        doReturn(true).when(map).get(customerId);
        doReturn(customerList).when(map).get(customerId);
        Customer c1 = new Customer();
        doReturn(c1).when(applicationContext).getBean(any(Class.class));
        Map<String, List<Customer>> mp = rewardPointsCalculatorService.calculateRewardPointsForCustomerId(customerId,amount);
        Assert.assertEquals(90.0,mp.get(customerId).get(0).getRewardPoints(),0.0);
    }

    @Test
    public void testCalculateRewardPointsExistingCustomerId() {
        String customerId = "fgi";
        Double amount = 120.0;
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setRewardPoints(90.0);
        customerList.add(customer);
        map.put(customerId,customerList);
        Customer c1 = new Customer();
        doReturn(c1).when(applicationContext).getBean(any(Class.class));
        Map<String, List<Customer>> mp = rewardPointsCalculatorService.calculateRewardPointsForCustomerId(customerId,amount);
        Assert.assertEquals(2,mp.get(customerId).size());
    }

    @Test
    public void testCalculateRewardPointsForNewCustomerIdWithDecimalAmount() {
        String customerId = "fgi";
        Double amount = 120.123;
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setRewardPoints(90.0);
        customerList.add(customer);
        doReturn(true).when(map).get(customerId);
        doReturn(customerList).when(map).get(customerId);
        Customer c1 = new Customer();
        doReturn(c1).when(applicationContext).getBean(any(Class.class));
        Map<String, List<Customer>> mp = rewardPointsCalculatorService.calculateRewardPointsForCustomerId(customerId,amount);
        Assert.assertEquals(1,mp.size());
    }

    @Test
    public void testCalculateRewardPointsForExistingCustomerIdWithDecimalPoint() {
        String customerId = "fgi";
        Double amount = 120.1;
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setRewardPoints(90.0);
        customerList.add(customer);
        map.put(customerId,customerList);
        Customer c1 = new Customer();
        doReturn(c1).when(applicationContext).getBean(any(Class.class));
        Map<String, List<Customer>> mp = rewardPointsCalculatorService.calculateRewardPointsForCustomerId(customerId,amount);
        Assert.assertEquals(2,mp.get(customerId).size());
    }
}
