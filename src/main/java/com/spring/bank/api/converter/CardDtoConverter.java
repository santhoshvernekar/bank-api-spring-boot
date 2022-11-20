package com.spring.bank.api.converter;

import com.spring.bank.api.model.dto.CardDto;
import com.spring.bank.api.model.entity.Card;
import org.springframework.beans.BeanUtils;

public class CardDtoConverter {
    /* Converter Functions  for Dto-Entity, We can use Lombok builders and Bean Utils as well */

    public static Card fromDto(CardDto dto) {
        Card converted = new Card();
        BeanUtils.copyProperties(dto, converted);
        return converted;
    }

    public static CardDto toDto(Card object) {
        CardDto converted = new CardDto();
        converted.setAvailableCardLimit(object.getAvailableCardLimit());
        converted.setExpiryDate(object.getExpiryDate());
        converted.setCvvNumber(object.getCvvNumber());
        converted.setCardType(object.getCardType());
        converted.setCardNumber(object.getCardNumber());
        return converted;
    }
}
