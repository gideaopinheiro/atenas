package com.atenas.domain.repository

import com.atenas.domain.entity.Client
import org.springframework.data.jpa.repository.JpaRepository

interface ClientRepository : JpaRepository<Client, Int> {}
