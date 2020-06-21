package com.atenas.service.impl

import com.atenas.domain.entity.Guy
import com.atenas.domain.repository.GuyRepository
import com.atenas.exceptions.InvalidPasswordException
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
class GuyServiceImpl (
        @Autowired val encoder: PasswordEncoder,
        @Autowired val guyRepository: GuyRepository): UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        val storedUser = guyRepository
                .findByLogin(login)
                .orElseThrow { ResponseStatusException(NOT_FOUND) }

        val roles: Array<String> = if (storedUser?.isAdmin()!!) arrayOf("ADMIN", "USER") else arrayOf("USER")

        return User.builder()
                .username(storedUser.login)
                .password(storedUser.password)
                .roles(*roles)
                .build()
    }

    fun authenticate(guy: Guy): UserDetails {
        val user = loadUserByUsername(guy.login)
        val matchPasswords: Boolean = encoder.matches(guy.password, user.password)

        return if (matchPasswords) user else throw InvalidPasswordException()
    }

    @Transactional
    fun saveGuy(guy: Guy): Guy {
        return guyRepository.save(guy)
    }
}
