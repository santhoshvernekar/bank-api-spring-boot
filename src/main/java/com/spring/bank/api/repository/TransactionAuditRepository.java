package com.spring.bank.api.repository;


import com.spring.bank.api.model.entity.TransactionAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
/*   - Repository pattern is used for Data Access
 * Here Dao pattern can be considered since underlying operations Differ, Not just simple find and Save
 * */
public interface TransactionAuditRepository extends JpaRepository<TransactionAudit, Long> {
    @Query("select t from TransactionAudit t where t.bankAccountId = :accountId")
    List<TransactionAudit> findAlTransactionsByAccountId(@Param("accountId") Long accountId);

    @Query("select t from TransactionAudit t where t.cardId = :cardId")
    List<TransactionAudit> findAlTransactionsByCardId(@Param("cardId") Long cardId);

    @Query("SELECT t FROM TransactionAudit t, Card c  WHERE t.cardId = c.id and c.cardNumber = :cardNumber")
    List<TransactionAudit> findAlTransactionsByCardNumber(@Param("cardNumber") Long cardNumber);

}
