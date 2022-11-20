package com.spring.bank.api.service;

import com.spring.bank.api.model.dto.WithdrawalActivityByCardDto;

public interface IWithdrawalActivityByCardService {
    void withDrawableByCard(WithdrawalActivityByCardDto request);
}
