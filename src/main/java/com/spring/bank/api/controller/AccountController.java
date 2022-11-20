package com.spring.bank.api.controller;

import com.google.common.base.Preconditions;
import com.spring.bank.api.converter.AccountDtoConverter;
import com.spring.bank.api.model.dto.AccountDto;
import com.spring.bank.api.model.dto.AccountList;
import com.spring.bank.api.model.dto.BalanceDto;
import com.spring.bank.api.model.dto.StatusResponse;
import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
 *  Account Controller - Mainly used for Account related Activity
 *  Assumptions Made :
 *  - An Account is associated with either DEBIT Card or CREDIT Card
 *  - One Customer will have only one CARD
 *
 * */
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    /*
     *   Requirement :
     *      - One rest endpoint to see current available balance in all accounts
     *
     * */
    @Operation(description = "Retrieve All Accounts Details")
    @GetMapping
    public ResponseEntity<AccountList> getAllAccounts() {

        AccountList list = new AccountList();
        list.setItems(accountService.getBankAccountList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(description = "Retrieve Account by given AccountId")
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable(name = "accountId") Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(AccountDtoConverter.toDto(account), HttpStatus.OK);
    }

    /*
     *
     *      - End point To Create Accounts
     *      - CustomerId is mandatory for Account creation, Account creation requires Valid Customer (Assumption)
     *      - Cards also created during Account Creation  (Assumption)
     *
     * */

    @Operation(description = "Create a new customer")
    @PutMapping
    public ResponseEntity<StatusResponse> saveAccount(@RequestBody @Valid AccountDto account) {
        Preconditions.checkNotNull(account, "Account can not be null");
        accountService.saveAccount(account.getCustomerId(), AccountDtoConverter.fromDto(account));
        return new ResponseEntity<>(new StatusResponse("Created Successfully"), HttpStatus.OK);

    }

    @GetMapping("/balances/{accountId}")
    public ResponseEntity<BalanceDto> getBalance(@PathVariable(name = "accountId") Long accountId) {
        Account account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(AccountDtoConverter.toBalanceDto(account), HttpStatus.OK);
    }
}
