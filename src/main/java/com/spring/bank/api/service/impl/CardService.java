package com.spring.bank.api.service.impl;

import com.google.common.base.Preconditions;
import com.spring.bank.api.error.BankApplicationException;
import com.spring.bank.api.model.entity.Card;
import com.spring.bank.api.repository.CardRepository;
import com.spring.bank.api.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardService implements ICardService {
    private final CardRepository cardRepository;

    @Override
    public Card getCardById(Long cardId) {
        Preconditions.checkNotNull(cardId);
        return cardRepository.findById(cardId)
                .orElseThrow(() -> BankApplicationException.to("** Card not found for id :: " + cardId));
    }

    @Override
    public Card getByCardNumber(Long cardNumber) {
        return cardRepository.getByCardNumber(cardNumber)
                .orElseThrow(() -> BankApplicationException.to("** Card not found for Number :: " + cardNumber));
    }
}
