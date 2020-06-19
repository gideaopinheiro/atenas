package com.atenas.rest.controller

import com.atenas.domain.entity.Guy
import com.atenas.service.impl.GuyServiceImpl
import org.springframework.http.HttpStatus.CREATED
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class GuyController (
        private val guyService: GuyServiceImpl,
        private val encoder: PasswordEncoder
) {
    @PostMapping
    @ResponseStatus(CREATED)
    fun save(@RequestBody @Valid guy: Guy): Guy {
        val encryptedPassword = encoder.encode(guy.password)
        guy.password = encryptedPassword
        return guyService.saveGuy(guy)
    }
}
