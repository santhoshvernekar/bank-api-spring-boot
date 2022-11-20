package com.spring.bank.api.service;

import com.spring.bank.api.model.entity.Card;

public interface ICardService {
    Card getCardById(Long cardId);
}
