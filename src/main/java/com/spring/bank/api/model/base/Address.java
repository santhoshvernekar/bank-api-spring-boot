package com.spring.bank.api.model.base;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Address implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "street_address")
    private String streetAddress;
    private String city;
    private String state;
    private String country;

    @Column(name = "postal_address")
    private String postalCode;
//    @JsonBackReference
//    @OneToOne(mappedBy="address",
//    		cascade = {
//    	    		CascadeType.MERGE,
//    	    		CascadeType.PERSIST,
//    	    		CascadeType.REMOVE
//    })
//    private BaseUser user;
}