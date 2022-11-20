package com.spring.bank.api.service.impl;

import com.spring.bank.api.model.dto.TransferActivityByAccountDto;
import com.spring.bank.api.model.dto.TransferActivityByCardDto;
import com.spring.bank.api.service.ICardService;
import com.spring.bank.api.service.ITransferActivityService;
import com.spring.bank.api.service.ITransferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class TransferActivityService implements ITransferActivityService {
    private final ITransferService transferService;
    private final ICardService cardService;

    @Override
    public void transferByAccountDetails(TransferActivityByAccountDto request) {
        transferService.transfer(request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount());
    }

    @Override
    public void transferByCardDetails(TransferActivityByCardDto request) {
        transferService.transfer(cardService.getCardById(request.getFromCardId()).getAccount().getId(), request.getToAccountId(), request.getAmount());
    }

}
