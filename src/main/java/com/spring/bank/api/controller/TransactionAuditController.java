package com.spring.bank.api.controller;

import com.spring.bank.api.model.dto.TransactionAudiListDto;
import com.spring.bank.api.service.ITransactionAuditService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * - Controller is for Retrieving Audits
 *
 *   Requirement :
 *      - It should be able to audit transfers or withdrawals
 * */
@RestController
@RequestMapping("/api/v1/transaction-audits")
public class TransactionAuditController {
    private final ITransactionAuditService transactionAuditService;

    @Autowired
    public TransactionAuditController(ITransactionAuditService transactionAuditService) {
        this.transactionAuditService = transactionAuditService;
    }


    @Operation(description = "Retrieve Audit for All Transactions")
    @GetMapping
    public ResponseEntity<TransactionAudiListDto> getAllTransactions() {
        TransactionAudiListDto list = new TransactionAudiListDto();
        list.setItems(transactionAuditService.getAllAudits());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(description = "Retrieve Audit of All Transactions by Account Id")
    @GetMapping(value = "/accounts/{accountId}")
    public ResponseEntity<TransactionAudiListDto> getAllTransactionByAccount(@PathVariable(name = "accountId") Long accountId) {
        TransactionAudiListDto list = new TransactionAudiListDto();
        list.setItems(transactionAuditService.getAuditByAccountId(accountId));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(description = "Retrieve Audit of All Transactions by cardId")
    @GetMapping(value = "/cards/{cardId}")
    public ResponseEntity<TransactionAudiListDto> getAuditByCardId(@PathVariable(name = "cardId") Long cardId) {
        TransactionAudiListDto list = new TransactionAudiListDto();
        list.setItems(transactionAuditService.getAuditByCardId(cardId));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(description = "Retrieve Audit of All Transactions by cardNumber")
    @GetMapping(value = "/filters")
    public ResponseEntity<TransactionAudiListDto> getAuditByCardNumber(@RequestParam(name = "cardNumber") Long cardNumber) {
        TransactionAudiListDto list = new TransactionAudiListDto();
        list.setItems(transactionAuditService.getAuditByCardNumber(cardNumber));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
