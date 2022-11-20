package com.spring.bank.api.model.dto;

import com.spring.bank.api.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private CardType cardType;
    private Long cardNumber;
    private String expiryDate;
    private String cvvNumber;
    private BigDecimal availableCardLimit;
}
