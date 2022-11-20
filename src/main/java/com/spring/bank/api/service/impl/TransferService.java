package com.spring.bank.api.service.impl;

import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.service.IAccountService;
import com.spring.bank.api.service.ITransactionService;
import com.spring.bank.api.service.ITransferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@AllArgsConstructor
public class TransferService implements ITransferService {

    private final IAccountService accountService;
    private final ITransactionService transactionService;

    @Override
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accountService.getAccountById(fromAccountId);
        Account toAccount = accountService.getAccountById(toAccountId);
        log.info("From Account Id: {}", fromAccount.getId().toString());
        log.info("To Account Id: {}", toAccount.getId().toString());
        transactionService.initiateTransfer(fromAccount, toAccount, amount);
    }
}
