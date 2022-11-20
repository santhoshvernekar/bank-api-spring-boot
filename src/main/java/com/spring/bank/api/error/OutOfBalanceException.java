package com.spring.bank.api.error;

public class OutOfBalanceException extends BankApplicationException {

    protected OutOfBalanceException(String format, Object... parameters) {
        super(format, parameters);
    }

    public static OutOfBalanceException to(String format, Object... parameters) {
        return new OutOfBalanceException(format, parameters);
    }

}
