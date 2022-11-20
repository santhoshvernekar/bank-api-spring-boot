package com.spring.bank.api.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.bank.api.model.base.BaseUser;
import com.spring.bank.api.model.dto.CustomerDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer extends BaseUser {
    private static final Logger LOGGER = LoggerFactory.getLogger(Customer.class);

    @Id
    @GenericGenerator(name = "customer_generator", strategy = "increment")
    @GeneratedValue(generator = "customer_generator")
    private Long id;
    @JsonBackReference
    @ToString.Exclude
    @OneToOne(cascade = {
            CascadeType.ALL,
    }, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    /* Converter Function for DTo Object */
    public static Customer fromDto(CustomerDto dto) {
        Customer converted = new Customer();
        BeanUtils.copyProperties(dto, converted);
        return converted;

    }
}
