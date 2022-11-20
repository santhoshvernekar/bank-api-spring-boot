package com.spring.bank.api.service;

import com.spring.bank.api.model.dto.TransferActivityByCardDto;

public interface ITransferActivityByCardService {
    void transferByCardDetails(TransferActivityByCardDto request);
}
