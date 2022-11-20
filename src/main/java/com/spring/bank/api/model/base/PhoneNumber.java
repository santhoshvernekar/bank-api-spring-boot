package com.spring.bank.api.model.base;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class PhoneNumber implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String type;
    private String number;
//    @JsonBackReference
//    @ManyToOne(cascade= { CascadeType.ALL})
//    @JoinColumn(name="customer_id")
//    private BaseUser user;

}