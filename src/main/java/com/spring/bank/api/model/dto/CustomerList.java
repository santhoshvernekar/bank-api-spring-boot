package com.spring.bank.api.model.dto;

import com.spring.bank.api.model.entity.Customer;
import lombok.Data;

import java.util.List;

@Data
public class CustomerList {
    List<Customer> items;
}

