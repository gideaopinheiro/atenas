package com.atenas.domain.repository

import com.atenas.domain.entity.CreditCard
import org.springframework.data.jpa.repository.JpaRepository

interface CreditCardRepository : JpaRepository<CreditCard, Int> { }
