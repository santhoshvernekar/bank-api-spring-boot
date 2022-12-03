package com.spring.bank.api.service;

import com.spring.bank.api.model.entity.Account;

public interface IAccountSaveService {
    void saveAccount(Long customerId, Account fromDto);
}
