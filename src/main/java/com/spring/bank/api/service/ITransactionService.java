package com.spring.bank.api.service;

import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.model.entity.TransactionAudit;

import java.math.BigDecimal;

public interface ITransactionService {
    void initiateWithdrawal(Account account, BigDecimal amount);

    void initiateTransfer(Account fromAccount, Account toAccount, BigDecimal amount);

    void receiveAmountFromSenderAccount(TransactionAudit.TransactionAuditBuilder transactionAuditBuilder, Account account, BigDecimal amount);

    void sendAmountToReceiverAccount(TransactionAudit.TransactionAuditBuilder transactionAuditBuilder, Account account, BigDecimal amount);
}
