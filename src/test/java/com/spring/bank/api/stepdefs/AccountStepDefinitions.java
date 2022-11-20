package com.spring.bank.api.stepdefs;

import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.model.entity.Card;
import com.spring.bank.api.service.IAccountService;
import com.spring.bank.api.service.ICardService;
import io.cucumber.java.en.Given;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Slf4j
public class AccountStepDefinitions extends CucumberIntegrationTest {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private ICardService cardService;

    @Given("Account with AccountId ${int} exist in System")
    public void account_$_exist_in_system(int int1) {
        Account account = accountService.getAccountById((long) int1);
        log.info("Account Details: {}", account.toString());
    }

    @Given("Check Balance for Account with AccountId ${int} in System")
    public void check_balance_in_Account(int int1) {
        Account account = accountService.getAccountById((long) int1);
        BigDecimal currentBalance = account.getCurrentBalance();
        log.info("Balance for Account with Id: {} , is : {}", int1, currentBalance);
    }

    @Given("Check Balance for Account with CardId ${int} in System")
    public void check_balance_in_Account_with_card_Id(int int1) {
        Card card = cardService.getCardById((long) int1);
        Account account = accountService.getAccountById(card.getAccount().getId());
        log.info("Balance for Account with Card ID: {}  is : {}", int1, account.getCurrentBalance());
    }
}
