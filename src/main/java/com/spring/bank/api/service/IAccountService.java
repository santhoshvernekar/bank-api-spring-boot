package com.spring.bank.api.service;

import com.spring.bank.api.model.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> getBankAccountList();

    Account getAccountById(Long accountId);

    void saveAccount(Long customerId, Account fromDto);

}
