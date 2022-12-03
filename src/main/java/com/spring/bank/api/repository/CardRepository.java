package com.spring.bank.api.repository;

import com.spring.bank.api.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
/*
 *
 * */
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select c from Card c where c.cardNumber = :cardNumber")
    Optional<Card> getByCardNumber(@Param("cardNumber") Long cardNumber);
}
