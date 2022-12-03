package com.spring.bank.api.utils;

import java.math.BigDecimal;

public class ValidationHelper {

    public static boolean isNegative(BigDecimal amount) {
        return BigDecimal.ZERO.compareTo(amount) > 0;
    }

    private ValidationHelper() {
        // Do Nothing
    }
}
