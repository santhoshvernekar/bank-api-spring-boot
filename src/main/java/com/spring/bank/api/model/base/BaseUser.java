package com.spring.bank.api.model.base;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class BaseUser extends BaseEntity {
    @NonNull
    private String firstName;  // Name can also be converted to Value Object (Complex type with validations)
    @NonNull
    private String lastName;
    private String email;  // -- Should be Value Object (Complex Type with Validations)
    private String phoneNumber;  // -- Should be Value Object (Complex Type with Validations) -  PhoneNumber class can be referred
    @NonNull
    private String title;
    private String address; // -- Should be Value Object (Complex Type with Validations) - Address Class can be referred

}
