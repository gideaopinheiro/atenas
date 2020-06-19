package com.atenas.service.impl

import com.atenas.domain.entity.Guy
import com.atenas.domain.repository.GuyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.*
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException


@Service
class GuyServiceImpl(
        @Autowired private val guyRepository: GuyRepository,
        @Autowired val encoder: PasswordEncoder
) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val storedUser = guyRepository
                .findByLogin(login)
                .orElseThrow { ResponseStatusException(NOT_FOUND) }

        val role: Array<String> = if (storedUser.isAdmin()) arrayOf("ADMIN", "USER") else arrayOf("USER")

        return User.builder()
                .username(storedUser.login)
                .password(storedUser.password)
                .roles(*role)
                .build()
    }

    @Transactional
    fun saveGuy(guy: Guy): Guy {
        return guyRepository.save(guy)
    }
}
