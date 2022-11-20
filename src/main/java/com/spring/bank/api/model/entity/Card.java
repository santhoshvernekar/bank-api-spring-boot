package com.spring.bank.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.bank.api.model.base.BaseEntity;
import com.spring.bank.api.model.enums.CardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Table(name = "card")
@EntityListeners(AuditingEntityListener.class)
public class Card extends BaseEntity {
    @Id
    @GenericGenerator(name = "card_generator", strategy = "increment")
    @GeneratedValue(generator = "card_generator")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private Long cardNumber;  // Value Type should be used (Complex Object)

    private String expiryDate;  //Date

    private String cvvNumber;

    private BigDecimal totalLimit;

    private BigDecimal availableCardLimit;

    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "card")
    private Account account;

}
