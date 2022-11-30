package com.spring.bank.api.service;

import com.spring.bank.api.model.entity.Account;

import java.math.BigDecimal;

public interface IAccountActivityService {
    /*
        These 2 methods should be abstracted from this Interface to have separation of concerns
        * */
    Account reduceBalance(Account account, BigDecimal totalAmount);

    Account increaseBalance(Account account, BigDecimal amount);
}
