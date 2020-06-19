package com.atenas.domain.repository

import com.atenas.domain.entity.Guy
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface GuyRepository : JpaRepository<Guy, Int> {
    fun findByLogin(login: String): Optional<Guy>
}