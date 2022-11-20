package com.spring.bank.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferActivityByCardDto {
    private Long fromCardId;
    private Long toAccountId;
    private BigDecimal amount;
    private String description;
}
