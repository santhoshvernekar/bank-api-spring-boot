package com.spring.bank.api.service;

import java.math.BigDecimal;

public interface ITransferService {
    void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount);
}
