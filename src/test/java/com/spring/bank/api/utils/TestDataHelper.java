package com.spring.bank.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.bank.api.model.dto.*;
import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.model.entity.Card;
import com.spring.bank.api.model.entity.Customer;
import com.spring.bank.api.model.enums.CardType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestDataHelper {

    public static final Long CUSTOMER_ID_FOR_NEW_BANK_ACCOUNT = 1L;
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String FIRST_NAME1 = "Santhosh";
    private static final String LAST_NAME1 = "Vernekar";
    private static final String TITLE = "Mr";
    private static final String TEST_EMAIL = "TEST_EMAIL@Email.com";
    private static final String TEST_PHONE_NUMBER = "123456789";

    public static CustomerDto getCustomerDto() {
        return CustomerDto.builder()
                .firstName(FIRST_NAME1)
                .lastName(LAST_NAME1)
                .title(TITLE)
                .email(TEST_EMAIL)
                .phoneNumber(TEST_PHONE_NUMBER)
                .build();
    }

    public static AccountDto getAccountDtoWithOutCard() {
        return AccountDto.builder()
                .currentBalance(BigDecimal.valueOf(1000))
                .build();
    }

    public static AccountDto getAccountDtoWithCard() {
        return AccountDto.builder()
                .currentBalance(BigDecimal.valueOf(1000))
                .card(CardDto.builder()
                        .cardType(CardType.DEBIT_CARD)
                        .expiryDate("2025-01-01")
                        .cardNumber(1414L)
                        .cvvNumber("789")
                        .build())
                .build();
    }

    public static Card getCard(Long id) {
        Card card1 = new Card();
        card1.setId(id);
        card1.setCardType(CardType.DEBIT_CARD);
        card1.setCardNumber(1234L);
        card1.setExpiryDate("2025-01-01");
        card1.setCvvNumber("123");
        //  card1.setAccount(getAccountInfoWithCard());
        card1.setTotalLimit(BigDecimal.valueOf(10000));
        return card1;
    }

    public static Account getAccountInfoWithCard(Long id) {
        Account account = new Account();
        account.setCurrentBalance(BigDecimal.valueOf(1000));
        account.setId(id);
        account.setCard(getCard(id));
        account.setCustomer(getCustomerObject(id));
        return account;
    }

    public static Customer getCustomerObject(Long id) {
        Customer customer = new Customer();
        // customer.setAccount(getAccountInfoWithCard());
        customer.setId(id);
        customer.setFirstName("Santhosh");
        customer.setLastName("Vernekar");
        customer.setEmail("santhosh@email.com");
        customer.setTitle("Mr");
        customer.setPhoneNumber("123456789");
        return customer;
    }

    public static List<Customer> getCustomerList(Long id) {
        List<Customer> list = new ArrayList<>();
        list.add(getCustomerObject(id));
        return list;
    }

    public static List<Account> getAccountList(Long id) {
        List<Account> list = new ArrayList<>();
        list.add(getAccountInfoWithCard(id));
        return list;
    }

    public static TransferActivityByAccountDto getTransferActivityByAccountDto() {
        return TransferActivityByAccountDto.builder().fromAccountId(1L).
                toAccountId(2L).amount(BigDecimal.valueOf(100)).description("Transfer").build();
    }

    public static TransferActivityByCardDto getTransferActivityByCardDto() {
        return TransferActivityByCardDto.builder()
                .amount(BigDecimal.valueOf(100))
                .fromCardId(1L).toAccountId(2L).description("TRANSFER")
                .build();
    }

    public static TransferActivityByCardDto getTransferActivityByCardDtoWithZeroAmount() {
        return TransferActivityByCardDto.builder().amount(BigDecimal.valueOf(0))
                .build();
    }

    public static TransferActivityByCardDto getTransferActivityByCardDtoWithNegativeAmount() {
        return TransferActivityByCardDto.builder().amount(BigDecimal.valueOf(-100))
                .build();
    }

    public static WithdrawalActivityByAccountDto getWithdrawalActivityByAccountDto() {
        return WithdrawalActivityByAccountDto.builder().amount(BigDecimal.valueOf(100)).accountId(1L).build();
    }

    public static WithdrawalActivityByCardDto getWithdrawalActivityByCardDto() {
        return WithdrawalActivityByCardDto.builder()
                .amount(BigDecimal.valueOf(100)).cardId(1L)
                .build();
    }

    public static WithdrawalActivityByCardDto getWithdrawalActivityByCardDtoWithZeroAmount() {
        return WithdrawalActivityByCardDto.builder()
                .amount(BigDecimal.valueOf(0)).cardId(1L)
                .build();
    }

    public static WithdrawalActivityByCardDto getWithdrawalActivityByCardDtoWithNegativeAmount() {
        return WithdrawalActivityByCardDto.builder()
                .amount(BigDecimal.valueOf(-100)).cardId(1L)
                .build();
    }

    public static Customer getNewCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("User");
        customer.setLastName("User");
        customer.setEmail("User@email.com");
        customer.setTitle("Mrs");
        return customer;
    }
}
