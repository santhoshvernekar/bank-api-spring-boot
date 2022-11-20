package com.spring.bank.api.repository;

import com.spring.bank.api.model.entity.Customer;
import com.spring.bank.api.utils.TestDataHelper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CustomerRepositoryTest {
    private final int existingLength = 4;
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void test_Find_All() {
        List<Customer> customerList = customerRepository.findAll();
        Assert.assertEquals(customerList.size(), existingLength);
    }

    @Test
    public void test_Find_All_After_Insertion() {
        customerRepository.save(TestDataHelper.getNewCustomer());
        List<Customer> customerList = customerRepository.findAll();
        Assert.assertEquals(customerList.size(), existingLength + 1);
    }

}
