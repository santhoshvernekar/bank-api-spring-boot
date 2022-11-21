package com.spring.bank.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.bank.api.model.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
/*
 AccountFactory Can be created when have different type of Accounts like creation of
 - CurrentAccount
 - SavingAccount
 - LoanAccount or AbstractFactory  -> HomeLoan, BusinessLoan, EducationLoan
 and so on from AccountFactory
*/
public class Account extends BaseEntity {

    @Id
    @GenericGenerator(name = "account_generator", strategy = "increment")
    @GeneratedValue(generator = "account_generator")
    private Long id;

    @NonNull
    private BigDecimal currentBalance;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "account")
    private Customer customer;

    @JsonBackReference
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id")
    private Card card;

}
