package com.spring.bank.api.model.dto;

import com.spring.bank.api.model.entity.TransactionAudit;
import lombok.Data;

import java.util.List;

@Data
public class TransactionAudiListDto {
    List<TransactionAudit> items;
}
