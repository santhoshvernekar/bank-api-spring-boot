package com.spring.bank.api.converter;

import com.spring.bank.api.model.dto.AccountDto;
import com.spring.bank.api.model.dto.BalanceDto;
import com.spring.bank.api.model.dto.CardDto;
import com.spring.bank.api.model.entity.Account;
import com.spring.bank.api.model.entity.Card;


public class AccountDtoConverter {
    /* Converter Functions  for Dto-Entity, We can use Lombok builders and Bean Utils as well */
    public static Account fromDto(AccountDto dto) {
        Account converted = new Account();
        Card card = new Card();
        card.setCardNumber(dto.getCard().getCardNumber());
        card.setCardType(dto.getCard().getCardType());
        card.setCvvNumber(dto.getCard().getCvvNumber());
        card.setExpiryDate(dto.getCard().getExpiryDate());
        card.setAvailableCardLimit(dto.getCard().getAvailableCardLimit());
        converted.setCard(card);
        converted.setCurrentBalance(dto.getCurrentBalance());
        return converted;
    }

    public static AccountDto toDto(Account entity) {
        AccountDto converted = new AccountDto();
        CardDto cardDto = new CardDto();
        cardDto.setCardNumber(entity.getCard().getCardNumber());
        cardDto.setCardType(entity.getCard().getCardType());
        cardDto.setCvvNumber(entity.getCard().getCvvNumber());
        cardDto.setExpiryDate(entity.getCard().getExpiryDate());
        cardDto.setAvailableCardLimit(entity.getCard().getAvailableCardLimit());
        converted.setCard(cardDto);
        converted.setCurrentBalance(entity.getCurrentBalance());
        converted.setCustomerId(entity.getCustomer().getId());
        converted.setCustomerId(entity.getId());
        return converted;
    }


    public static BalanceDto toBalanceDto(Account entity) {
        BalanceDto converted = new BalanceDto();
        converted.setCurrentBalance(entity.getCurrentBalance());
        converted.setAccountId(entity.getId());
        return converted;
    }
}
