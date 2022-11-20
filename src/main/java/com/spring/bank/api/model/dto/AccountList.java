package com.spring.bank.api.model.dto;

import com.spring.bank.api.model.entity.Account;
import lombok.Data;

import java.util.List;

@Data
public class AccountList {
    List<Account> items;
}
