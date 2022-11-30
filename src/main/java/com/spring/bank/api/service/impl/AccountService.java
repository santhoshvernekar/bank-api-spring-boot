package com.spring.bank.api.service.impl;

import com.google.common.base.Preconditions;
import com.spring.bank.api.error.BankApplicationException;
import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.model.entity.Card;
import com.spring.bank.api.model.entity.Customer;
import com.spring.bank.api.repository.AccountRepository;
import com.spring.bank.api.repository.CardRepository;
import com.spring.bank.api.service.IAccountActivityService;
import com.spring.bank.api.service.IAccountService;
import com.spring.bank.api.service.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class AccountService implements IAccountService, IAccountActivityService {
    private static final String MESSAGE_FORMAT_NO_ACCOUNT = "No Account by AccountId: %s";
    private final AccountRepository accountRepository;
    // Below dependencies can be extracted from Class with another service
    private final ICustomerService customerService;
    private final CardRepository cardRepository;

    public List<Account> getBankAccountList() {
        return accountRepository.findAll();
    }

    private void preChecksBeforeSave(Account account) {
        Preconditions.checkNotNull(account, "Account object is Invalid");
        Preconditions.checkNotNull(account.getCurrentBalance(), "CurrentBalance value is Invalid");
        Preconditions.checkArgument(account.getCurrentBalance().compareTo(BigDecimal.ZERO) > -1, "CurrentBalance can not be negative");
    }

    public Account getAccountById(Long accountId) {
        Preconditions.checkNotNull(accountId, MESSAGE_FORMAT_NO_ACCOUNT, accountId);

        return accountRepository.findById(accountId)
                .orElseThrow(() -> BankApplicationException.to("** Account not found for id :: " + accountId));
    }

    // This can be moved another Interface for Separation of concern
    public Account reduceBalance(Account account, BigDecimal amount) {
        Long accountId = account.getId();
        int rowsAffected = accountRepository.reduceBalance(accountId, amount);
        if (rowsAffected == 0) throw BankApplicationException.to(
                "Withdrawal transaction is either failed or not completed");
        return accountRepository.findById(accountId)
                .orElseThrow(() -> BankApplicationException.to("** Account not found for id :: " + account.getId()));
    }

    // This can be moved another Interface for Separation of concern
    public Account increaseBalance(Account account, BigDecimal amount) {
        Long accountId = account.getId();
        int rowsAffected = accountRepository.increaseBalance(accountId, amount);
        if (rowsAffected == 0) throw BankApplicationException.to(
                "Transfer transaction is either failed or not completed");
        return accountRepository.findById(accountId)
                .orElseThrow(() -> BankApplicationException.to("** Account not found for id :: " + account.getId()));
    }

    public void saveAccount(Long customerId, @Valid Account account) {
        preChecksBeforeSave(account);
        Customer customer = customerService.getCustomer(customerId);
        account.setCustomer(customer);
        Card card = account.getCard();
        //  account.setCard(null);
        Account savedAccount = accountRepository.save(account);
        card.setAccount(savedAccount);
        cardRepository.save(card);
    }
}
