package com.spring.bank.api.service.impl;

import com.spring.bank.api.model.entity.TransactionAudit;
import com.spring.bank.api.repository.TransactionAuditRepository;
import com.spring.bank.api.service.ITransactionAuditService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionAuditService implements ITransactionAuditService {
    private final TransactionAuditRepository transactionAuditRepository;

    @Override
    public List<TransactionAudit> getAllAudits() {
        return transactionAuditRepository.findAll();
    }

    @Override
    public List<TransactionAudit> getAuditByAccountId(Long accountId) {
        return transactionAuditRepository.findAlTransactionsByAccountId(accountId);
    }

    @Override
    public List<TransactionAudit> getAuditByCardId(Long cardId) {
        return transactionAuditRepository.findAlTransactionsByCardId(cardId);
    }

    @Override
    public List<TransactionAudit> getAuditByCardNumber(Long cardNumber) {
        return transactionAuditRepository.findAlTransactionsByCardNumber(cardNumber);
    }

    public void saveLogs(List<TransactionAudit> logs) {
        transactionAuditRepository.saveAll(logs);
    }

}
