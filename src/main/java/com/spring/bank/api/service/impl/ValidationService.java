package com.spring.bank.api.service.impl;

import com.google.common.base.Preconditions;
import com.spring.bank.api.error.OutOfBalanceException;
import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.service.IValidationService;
import com.spring.bank.api.utils.ValidationHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ValidationService implements IValidationService {

    @Override
    public void validateWithdrawalCondition(Account account, BigDecimal amount) {
        if (ValidationHelper.isNegative(account.getCurrentBalance().subtract(amount))) {
            throw OutOfBalanceException.to(
                    "Current balance is not available in Account to withdraw. current balance: %s, requested amount: %s",
                    account.getCurrentBalance(),
                    amount);
        }
    }

    @Override
    public void validateCurrentBalance(Account account) {
        if (ValidationHelper.isNegative(account.getCurrentBalance())) {
            throw OutOfBalanceException.to(
                    "Current balance is not available in Account to withdraw/transfer. current balance: %s",
                    account.getCurrentBalance());
        }
    }

    @Override
    public void validateAmountValue(BigDecimal amount) {
        Preconditions.checkNotNull(amount, "Amount Object is Not Accessible");
        Preconditions.checkArgument(!ValidationHelper.isNegative(amount), "Amount can't be negative");
    }
}
