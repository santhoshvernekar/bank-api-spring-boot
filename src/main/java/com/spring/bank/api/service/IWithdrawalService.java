package com.spring.bank.api.service;

import java.math.BigDecimal;

public interface IWithdrawalService {
    void withdraw(Long accountId, BigDecimal amount);
}
