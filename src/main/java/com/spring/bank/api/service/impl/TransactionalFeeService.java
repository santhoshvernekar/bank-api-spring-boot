package com.spring.bank.api.service.impl;

import com.spring.bank.api.error.BankApplicationException;
import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.model.enums.ActivityType;
import com.spring.bank.api.service.ITransactionalFeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Slf4j
@Service
public class TransactionalFeeService implements ITransactionalFeeService {

    private static final MathContext mathContext = new MathContext(7, RoundingMode.HALF_UP);
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
    // Should be Parameterized, ideally from Config/Datastore
    private static final BigDecimal CC_TRANSFER_FEE = BigDecimal.ONE.divide(HUNDRED, mathContext); // 1%
    private static final BigDecimal CC_WITHDRAWAL_FEE = BigDecimal.ONE.divide(HUNDRED, mathContext); // 1%

    @Override
    public BigDecimal getFees(ActivityType activityType, Account account, BigDecimal amount) {
        BigDecimal fee;
        final String FEE_MESSAGE = "Calculated Fee Amount for : {} is {}";
        switch (account.getCard().getCardType()) {
            case DEBIT_CARD:
                fee = BigDecimal.ZERO;
                break;
            case CREDIT_CARD:

                if (ActivityType.TRANSFER.equals(activityType)) {
                    fee = CC_TRANSFER_FEE.multiply(amount, mathContext); // 0.01 * Amount
                    log.info(FEE_MESSAGE, activityType, fee.toString());
                    break;
                } else if (ActivityType.WITHDRAW.equals(activityType)) {
                    fee = CC_WITHDRAWAL_FEE.multiply(amount, mathContext); // 0.01 * Amount
                    log.info(FEE_MESSAGE, activityType, fee.toString());
                    break;
                }
            default:
                throw BankApplicationException.to("Unknown ActivityType: %s", activityType);
        }
        return fee;

    }

    @Override
    public BigDecimal getTotalAmount(BigDecimal amount, BigDecimal fee) {
        return amount.add(fee, mathContext);
    }

}
