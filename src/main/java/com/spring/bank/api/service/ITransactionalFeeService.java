package com.spring.bank.api.service;

import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.model.enums.ActivityType;

import java.math.BigDecimal;

public interface ITransactionalFeeService {
    BigDecimal getFees(ActivityType activityType, Account account, BigDecimal amount);

    BigDecimal getTotalAmount(BigDecimal amount, BigDecimal fee);
}
