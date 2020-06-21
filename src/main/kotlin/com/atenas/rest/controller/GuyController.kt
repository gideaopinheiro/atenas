package com.atenas.rest.controller

import com.atenas.domain.entity.Guy
import com.atenas.exceptions.InvalidPasswordException
import com.atenas.rest.dto.CredentialsDTO
import com.atenas.rest.dto.TokenDTO
import com.atenas.security.jwt.JwtService
import com.atenas.service.impl.GuyServiceImpl
import org.springframework.http.HttpStatus.*
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class GuyController(
        private val guyService: GuyServiceImpl,
        private val encoder: PasswordEncoder,
        private val jwtService: JwtService
) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun save(@RequestBody @Valid guy: Guy): Guy {
        val encryptedPassword: String = encoder.encode(guy.password)
        guy.password = encryptedPassword
        return guyService.saveGuy(guy)
    }

    @PostMapping("/auth")
    fun authenticate(@RequestBody credentials: CredentialsDTO): TokenDTO {
        return try{
            //TODO(Alterar a forma como o objeto guy é criado)
            val guy = Guy(-1, credentials.login, credentials.password)
            val authenticatedGuy = guyService.authenticate(guy)
            val token: String = jwtService.generateToken(guy)
            TokenDTO(authenticatedGuy.username, token)
        } catch (e: UsernameNotFoundException) {
            throw ResponseStatusException(UNAUTHORIZED, e.message)
        } catch (e: InvalidPasswordException) {
            throw ResponseStatusException(UNAUTHORIZED, e.message)
        }
    }

}
