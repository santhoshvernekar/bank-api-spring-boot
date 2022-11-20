package com.spring.bank.api.service;

import com.spring.bank.api.model.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountService {
    List<Account> getBankAccountList();

    Account getAccountById(Long accountId);

    void saveAccount(Long customerId, Account fromDto);

    Account reduceBalance(Account account, BigDecimal totalAmount);

    Account increaseBalance(Account account, BigDecimal amount);
}
