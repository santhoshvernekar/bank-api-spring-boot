package com.spring.bank.api.model.entity;

import com.spring.bank.api.model.enums.ActionType;
import com.spring.bank.api.model.enums.ActivityStatus;
import com.spring.bank.api.model.enums.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction_log")
@EntityListeners(AuditingEntityListener.class)
public class TransactionAudit {

    @Id
    @GenericGenerator(name = "transaction_generator", strategy = "increment")
    @GeneratedValue(generator = "transaction_generator")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    @Enumerated(EnumType.STRING)
    private ActionType action;

    private Long bankAccountId;
    private Long customerId;
    private Long cardId;

    private BigDecimal amount;

    private BigDecimal fee;

    private BigDecimal totalAmount;

    private BigDecimal beforeBalance;

    private BigDecimal afterBalance;

    @Enumerated(EnumType.STRING)
    private ActivityStatus status;

    private String remarks;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;
}
