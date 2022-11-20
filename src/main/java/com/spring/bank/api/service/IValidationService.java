package com.spring.bank.api.service;

import com.spring.bank.api.model.entity.Account;

import java.math.BigDecimal;

public interface IValidationService {
    void validateWithdrawalCondition(Account account, BigDecimal amount);

    void validateCurrentBalance(Account account);

    void validateAmountValue(BigDecimal amount);
}
