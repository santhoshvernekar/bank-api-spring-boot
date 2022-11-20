package com.spring.bank.api.service;

import com.spring.bank.api.model.entity.TransactionAudit;

import java.util.List;

public interface ITransactionAuditService {
    List<TransactionAudit> getAllAudits();

    List<TransactionAudit> getAuditByAccountId(Long accountId);

    List<TransactionAudit> getAuditByCardId(Long cardId);

    List<TransactionAudit> getAuditByCardNumber(Long cardNumber);

    void saveLogs(List<TransactionAudit> logs);
}
