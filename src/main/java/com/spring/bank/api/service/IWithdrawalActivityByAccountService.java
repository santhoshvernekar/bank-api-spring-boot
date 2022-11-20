package com.spring.bank.api.service;

import com.spring.bank.api.model.dto.WithdrawalActivityByAccountDto;

public interface IWithdrawalActivityByAccountService {
    void withDrawableByAccount(WithdrawalActivityByAccountDto request);
}
