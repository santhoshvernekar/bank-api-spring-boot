package com.spring.bank.api.repository;

import com.spring.bank.api.model.entity.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void test_Reduce_Balance() {
        List<Account> listBefore = accountRepository.findAll();
        BigDecimal previousAmount = listBefore.get(0).getCurrentBalance();
        accountRepository.reduceBalance(listBefore.get(0).getId(), BigDecimal.valueOf(100));
        List<Account> listAfter = accountRepository.findAll();
        BigDecimal reducedAmount = listAfter.get(0).getCurrentBalance();
        BigDecimal difference = previousAmount.subtract(reducedAmount);
        Assert.assertEquals(difference.intValue(), 100);

    }

    @Test
    public void test_Increase_Balance() {
        List<Account> listBefore = accountRepository.findAll();
        BigDecimal previousAmount = listBefore.get(0).getCurrentBalance();
        accountRepository.increaseBalance(listBefore.get(0).getId(), BigDecimal.valueOf(100));
        List<Account> listAfter = accountRepository.findAll();
        BigDecimal increasedAmount = listAfter.get(0).getCurrentBalance();
        BigDecimal difference = increasedAmount.subtract(previousAmount);
        Assert.assertEquals(difference.intValue(), 100);
    }

}
