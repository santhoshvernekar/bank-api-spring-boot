package com.spring.bank.api.service.impl;

import com.google.common.base.Preconditions;
import com.spring.bank.api.error.OutOfBalanceException;
import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.model.entity.TransactionAudit;
import com.spring.bank.api.model.enums.ActionType;
import com.spring.bank.api.model.enums.ActivityStatus;
import com.spring.bank.api.model.enums.ActivityType;
import com.spring.bank.api.service.*;
import com.spring.bank.api.utils.TransactionAuditHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionService implements ITransactionService {
    private final IValidationService validationService;
    private final ITransactionalFeeService transactionalFeeService;
    private final IAccountService accountService;
    private final ITransactionAuditService transactionAuditService;

    @Override
    public void initiateWithdrawal(Account account, BigDecimal amount) {
        Preconditions.checkNotNull(account, "Account object is Invalid");
        validationService.validateAmountValue(amount);
        List<TransactionAudit> logs = new ArrayList<>();
        TransactionAudit.TransactionAuditBuilder transaction =
                TransactionAuditHelper.getRegularTransactionLog(ActivityType.WITHDRAW, account, ActionType.DEBIT, amount);
        try {
            receiveAmountFromSenderAccount(transaction, account, amount);
            logs.add(transaction.remarks(ActivityStatus.SUCCESS.name()).build());
        } catch (OutOfBalanceException ex) {
            logs.add(TransactionAuditHelper.getFailureTransactionLog(transaction, ActivityStatus.NO_BALANCE, ex.getMessage()).build());
            throw ex;
        } catch (Exception ex) {
            logs.add(TransactionAuditHelper.getFailureTransactionLog(transaction, ActivityStatus.FAILURE, ex.getMessage()).build());
            throw ex;
        } finally {
            transactionAuditService.saveLogs(logs);
        }
    }

    @Override
    public void initiateTransfer(Account fromAccount, Account toAccount, BigDecimal amount) {
        basicChecksBeforeTransfer(fromAccount, toAccount);
        List<TransactionAudit> logs = new ArrayList<>();
        TransactionAudit.TransactionAuditBuilder fromAccountTransaction =
                TransactionAuditHelper.getRegularTransactionLog(ActivityType.TRANSFER, fromAccount, ActionType.DEBIT, amount);
        TransactionAudit.TransactionAuditBuilder toAccountTransaction =
                TransactionAuditHelper.getRegularTransactionLog(ActivityType.TRANSFER, toAccount, ActionType.CREDIT, amount);
        try {
            validationService.validateAmountValue(amount);
            receiveAmountFromSenderAccount(fromAccountTransaction, fromAccount, amount);
            sendAmountToReceiverAccount(toAccountTransaction, toAccount, amount);
            logs.add(fromAccountTransaction.remarks(ActivityStatus.SUCCESS.name()).build());
            logs.add(toAccountTransaction.build());
        } catch (OutOfBalanceException ex) {
            logs.add(TransactionAuditHelper.getFailureTransactionLog(fromAccountTransaction, ActivityStatus.NO_BALANCE, ex.getMessage()).build());
            logs.add(TransactionAuditHelper.getFailureTransactionLog(toAccountTransaction, ActivityStatus.NO_BALANCE, ex.getMessage()).build());
            throw ex;
        } catch (Exception ex) {
            logs.add(TransactionAuditHelper.getFailureTransactionLog(fromAccountTransaction, ActivityStatus.FAILURE, ex.getMessage()).build());
            logs.add(TransactionAuditHelper.getFailureTransactionLog(toAccountTransaction, ActivityStatus.FAILURE, ex.getMessage()).build());
            throw ex;

        } finally {
            transactionAuditService.saveLogs(logs);
        }


    }

    private void basicChecksBeforeTransfer(Account fromAccount, Account toAccount) {
        Preconditions.checkNotNull(fromAccount, "fromAccount doesn't Exist");
        Preconditions.checkNotNull(toAccount, "toAccount doesn't Exist");
        Preconditions.checkArgument(!Objects.equals(fromAccount.getId(), toAccount.getId()),
                "Transfer is not possible to the same account. Account Id: ",
                fromAccount.getId());
    }

    @Override
    public void receiveAmountFromSenderAccount(TransactionAudit.TransactionAuditBuilder transactionAuditBuilder, Account account, BigDecimal amount) {

        BigDecimal calculatedFee = transactionalFeeService.getFees(ActivityType.WITHDRAW, account, amount);
        BigDecimal totalAmount = transactionalFeeService.getTotalAmount(amount, calculatedFee);

        validationService.validateWithdrawalCondition(account, totalAmount);

        Account senderAccount = accountService.reduceBalance(account, totalAmount);
        validationService.validateCurrentBalance(senderAccount);

        transactionAuditBuilder.status(ActivityStatus.SUCCESS)
                .fee(calculatedFee)
                .totalAmount(totalAmount)
                .afterBalance(senderAccount.getCurrentBalance());

    }

    @Override
    public void sendAmountToReceiverAccount(TransactionAudit.TransactionAuditBuilder transactionAuditBuilder, Account account, BigDecimal amount) {
        Account receiverAccount = accountService.increaseBalance(account, amount);
        transactionAuditBuilder.status(ActivityStatus.SUCCESS)
                .fee(BigDecimal.ZERO)
                .totalAmount(amount)
                .afterBalance(receiverAccount.getCurrentBalance());
    }
}
