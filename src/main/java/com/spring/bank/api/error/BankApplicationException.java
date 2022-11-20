package com.spring.bank.api.error;

public class BankApplicationException extends RuntimeException {

    protected BankApplicationException(String format, Object... parameters) {
        super(String.format(format, parameters));
    }

    protected BankApplicationException(Throwable cause, String format, Object... parameters) {
        super(String.format(format, parameters), cause);
    }

    public static BankApplicationException to(String format, Object... parameters) {
        return new BankApplicationException(format, parameters);
    }

    public static BankApplicationException to(Throwable cause, String format, Object... parameters) {
        return new BankApplicationException(cause, format, parameters);
    }
}
