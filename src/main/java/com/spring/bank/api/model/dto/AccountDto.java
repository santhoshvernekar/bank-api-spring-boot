package com.spring.bank.api.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDto {

    private BigDecimal currentBalance;
    private CardDto card;
    private Long customerId;
}
