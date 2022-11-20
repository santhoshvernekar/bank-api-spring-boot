package com.spring.bank.api.service.impl;

import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.service.IAccountService;
import com.spring.bank.api.service.ITransactionService;
import com.spring.bank.api.service.IWithdrawalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class WithdrawalService implements IWithdrawalService {

    private final ITransactionService transactionService;
    private final IAccountService accountService;

    @Override
    public void withdraw(Long accountId, BigDecimal amount) {
        Account account = accountService.getAccountById(accountId);
        transactionService.initiateWithdrawal(account, amount);
    }
}
