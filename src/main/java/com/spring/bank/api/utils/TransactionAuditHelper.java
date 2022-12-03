package com.spring.bank.api.utils;

import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.model.entity.TransactionAudit;
import com.spring.bank.api.model.enums.ActionType;
import com.spring.bank.api.model.enums.ActivityStatus;
import com.spring.bank.api.model.enums.ActivityType;

import java.math.BigDecimal;

public class TransactionAuditHelper {
/*
 * used for just building the Objects using Lombok builder
 * */
    public static TransactionAudit.TransactionAuditBuilder getRegularTransactionLog(ActivityType transactionType,
                                                                                    Account account, ActionType action, BigDecimal amount) {
        return TransactionAudit.builder()
                .type(transactionType)
                .action(action)
                .amount(amount)
                .customerId(account.getCustomer().getId())
                .bankAccountId(account.getId())
                .cardId(account.getCard().getId())
                .beforeBalance(account.getCurrentBalance());
    }

    public static TransactionAudit.TransactionAuditBuilder getFailureTransactionLog(TransactionAudit.TransactionAuditBuilder transactionAudit,
                                                                                    ActivityStatus activityStatus,
                                                                                    String remark) {
        return transactionAudit.status(activityStatus).remarks(remark);
    }

    private TransactionAuditHelper() {
        // Do Nothing
    }

}
