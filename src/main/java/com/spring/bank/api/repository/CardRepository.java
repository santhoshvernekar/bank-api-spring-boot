package com.spring.bank.api.repository;

import com.spring.bank.api.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
/*
 * Repository pattern would be appropriate for Simple Operations
 * */
public interface CardRepository extends JpaRepository<Card, Long> {
}
