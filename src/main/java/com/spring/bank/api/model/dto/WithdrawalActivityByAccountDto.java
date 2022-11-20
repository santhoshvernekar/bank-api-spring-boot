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
public class WithdrawalActivityByAccountDto {
    private Long accountId;
    private BigDecimal amount;
}
