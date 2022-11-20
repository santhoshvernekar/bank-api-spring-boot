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
/*
 *  Simple POJO
 * */
public class BalanceDto {
    private Long accountId;
    private BigDecimal currentBalance;
}
