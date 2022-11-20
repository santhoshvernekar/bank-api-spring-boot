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


//    public static Account fromDto(AccountDto dto) {
//        Account converted = new Account();
//        BeanUtils.copyProperties(dto, converted);
//        CardDto cardDto= dto.getCard();
//        Card cardConverted = new Card();
//        BeanUtils.copyProperties(cardDto, cardConverted);
//        converted.setCard(cardConverted);
//        return converted;
//    }
//
//    public static AccountDto toDto(Account entity) {
//        AccountDto converted = new AccountDto();
//        CardDto cardDto = new CardDto();
//        cardDto.setCardNumber(entity.getCard().getCardNumber());
//        cardDto.setCardType(entity.getCard().getCardType());
//        cardDto.setCvvNumber(entity.getCard().getCvvNumber());
//        cardDto.setExpiryDate(entity.getCard().getExpiryDate());
//        cardDto.setAvailableCardLimit(entity.getCard().getAvailableCardLimit());
//        converted.setCard(cardDto);
//        converted.setCurrentBalance(entity.getCurrentBalance());
//        converted.setCustomerId(entity.getCustomer().getId());
//        converted.setCustomerId(entity.getId());
//        return converted;
//    }
//
//    public  static BalanceDto toBalanceDto(Account entity) {
//        BalanceDto converted = new BalanceDto();
//        converted.setAccountId(entity.getId());
//        converted.setCurrentBalance(entity.getCurrentBalance());
//        return converted;
//    }

}
