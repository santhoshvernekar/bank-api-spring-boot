package com.spring.bank.api.converter;


import com.spring.bank.api.model.dto.CustomerDto;
import com.spring.bank.api.model.entity.Customer;
import org.springframework.beans.BeanUtils;

public class CustomerToDtoConverter {

    /* Converter Functions  for Dto-Entity, We can use Lombok builders and Bean Utils as well */
    public static Customer fromDto(CustomerDto dto) {
        Customer converted = new Customer();
        BeanUtils.copyProperties(dto, converted);
        return converted;
    }

    public static CustomerDto toDto(Customer object) {
        CustomerDto converted = new CustomerDto();
        converted.setEmail(object.getEmail());
        converted.setFirstName(object.getFirstName());
        converted.setLastName(object.getLastName());
        converted.setTitle(object.getTitle());
        return converted;
    }

}