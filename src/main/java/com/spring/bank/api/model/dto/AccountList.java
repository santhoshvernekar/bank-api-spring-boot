package com.spring.bank.api.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountList {
    List<AccountDto> items;
}
