package com.spring.bank.api.service;

import com.spring.bank.api.model.dto.TransferActivityByAccountDto;

public interface ITransferActivityByAccountService {
    void transferByAccountDetails(TransferActivityByAccountDto request);
}
