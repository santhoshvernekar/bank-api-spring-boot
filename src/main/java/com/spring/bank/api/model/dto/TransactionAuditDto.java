package com.spring.bank.api.model.dto;

import com.spring.bank.api.model.enums.ActionType;
import com.spring.bank.api.model.enums.ActivityStatus;
import com.spring.bank.api.model.enums.ActivityType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionAuditDto {

    private ActivityType type;
    private ActionType action;
    private Long bankAccountId;
    private Long customerId;
    private Long cardId;
    private BigDecimal amount;
    private BigDecimal fee;
    private BigDecimal totalAmount;
    private BigDecimal beforeBalance;
    private BigDecimal afterBalance;
    private ActivityStatus status;
    private String remarks;
    private Date createdDate;
    private Date lastModifiedDate;
}
