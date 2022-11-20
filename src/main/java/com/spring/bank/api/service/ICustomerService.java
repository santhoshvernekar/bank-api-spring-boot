package com.spring.bank.api.service;

import com.spring.bank.api.model.entity.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> getCustomerList();

    Customer getCustomer(Long customerId);

    Customer saveCustomer(Customer customer);
}
