package com.spring.bank.api.repository;

import com.spring.bank.api.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
/*
   - Repository pattern is used for Data Access
 * - Here Dao pattern can be considered since underlying operations Differ, Not just simple find and Save
 * */
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Modifying(clearAutomatically = true)
    @Query("update Account b set b.currentBalance = b.currentBalance - :amount where b.id = :accountId")
    int reduceBalance(@Param("accountId") Long accountId, @Param("amount") BigDecimal amount);

    @Modifying(clearAutomatically = true)
    @Query("update Account b set b.currentBalance = b.currentBalance + :amount where b.id = :accountId")
    int increaseBalance(@Param("accountId") Long accountId, @Param("amount") BigDecimal amount);
}
