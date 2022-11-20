package com.spring.bank.api.service.impl;

import com.google.common.base.Preconditions;
import com.spring.bank.api.error.BankApplicationException;
import com.spring.bank.api.model.entity.Customer;
import com.spring.bank.api.repository.CustomerRepository;
import com.spring.bank.api.service.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(Long customerId) {
        Preconditions.checkNotNull(customerId, "No customer by customerId: %d", customerId);
        return customerRepository.findById(customerId)
                .orElseThrow(() -> BankApplicationException.to("** Customer not found for id :: " + customerId));
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Preconditions.checkNotNull(customer, "Customer object is Invalid");
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer saved With Id: {}", savedCustomer.getId());
        return savedCustomer;
    }
}
