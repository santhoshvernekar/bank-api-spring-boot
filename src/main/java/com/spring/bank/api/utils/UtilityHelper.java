package com.spring.bank.api.utils;

import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.model.entity.Card;
import com.spring.bank.api.model.entity.Customer;
import com.spring.bank.api.model.enums.CardType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class UtilityHelper {

    public static final Supplier<List<Customer>> customerSupplier = () -> {

        Customer customer1 = new Customer();
        Account account1 = new Account();
        Card card1 = new Card();
        card1.setCardType(CardType.DEBIT_CARD);
        card1.setCardNumber(1234L);
        card1.setExpiryDate("2025-01-01");
        card1.setCvvNumber("123");
        account1.setCurrentBalance(new BigDecimal("1000"));
        account1.setCard(card1);
        customer1.setAccount(account1);
        customer1.setFirstName("Santhosh");
        customer1.setLastName("Vernekar");
        customer1.setEmail("santhosh@email.com");
        customer1.setTitle("Mr");

        Customer customer2 = new Customer();

        Account account2 = new Account();
        Card card2 = new Card();
        card2.setCardType(CardType.CREDIT_CARD);
        card2.setCardNumber(4567L);
        card2.setExpiryDate("2025-01-01");
        card2.setCvvNumber("123");
        account2.setCurrentBalance(new BigDecimal("1000"));
        account2.setCard(card2);
        customer2.setAccount(account2);
        customer2.setFirstName("Marteen");
        customer2.setLastName("J");
        customer2.setEmail("marteen@email.com");
        customer2.setTitle("Mr");

        Customer customer3 = new Customer();

        Account account3 = new Account();
        Card card3 = new Card();
        card3.setCardType(CardType.CREDIT_CARD);
        card3.setCardNumber(7894L);
        card3.setExpiryDate("2025-01-01");
        card3.setCvvNumber("425");
        account3.setCurrentBalance(BigDecimal.ZERO);
        account3.setCard(card3);
        customer3.setAccount(account3);
        customer3.setFirstName("Naveen");
        customer3.setLastName("M");
        customer3.setEmail("naveen@email.com");
        customer3.setTitle("Mr");


        Customer customer4 = new Customer();
        Account account4 = new Account();
        Card card4 = new Card();
        card4.setCardType(CardType.CREDIT_CARD);
        card4.setCardNumber(6547L);
        card4.setExpiryDate("2025-01-01");
        card4.setCvvNumber("526");
        account4.setCurrentBalance(new BigDecimal("20000"));
        account4.setCard(card4);
        customer4.setAccount(account4);
        customer4.setFirstName("Maria");
        customer4.setLastName("P");
        customer4.setEmail("maria@email.com");
        customer4.setTitle("Mrs");

        return Arrays.asList(customer1, customer2, customer3, customer4);

    };

    private UtilityHelper() {
    }

}
