package com.spring.bank.api.service.impl;

import com.spring.bank.api.model.dto.WithdrawalActivityByAccountDto;
import com.spring.bank.api.model.dto.WithdrawalActivityByCardDto;
import com.spring.bank.api.service.ICardService;
import com.spring.bank.api.service.IWithdrawalActivityService;
import com.spring.bank.api.service.IWithdrawalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WithdrawalActivityService implements IWithdrawalActivityService {
    private final ICardService cardService;
    private final IWithdrawalService withdrawalService;

    @Override
    public void withDrawableByAccount(WithdrawalActivityByAccountDto request) {
        withdrawalService.withdraw(request.getAccountId(), request.getAmount());
    }

    @Override
    public void withDrawableByCard(WithdrawalActivityByCardDto request) {
        withdrawalService.withdraw(cardService.getCardById(request.getCardId()).getAccount().getId(), request.getAmount());
    }
}
