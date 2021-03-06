package com.atenas.rest.controller

import com.atenas.domain.entity.CreditCard
import com.atenas.domain.repository.ClientRepository
import com.atenas.domain.repository.CreditCardRepository
import com.atenas.exceptions.CustomException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1")
class PaymentMethodController (
        @Autowired private val clientRepository: ClientRepository,
        @Autowired private val creditCardRepository: CreditCardRepository
) {

    @PostMapping("/credit-card/{userId}")
    @ResponseStatus(CREATED)
    fun registerCreditCard(@PathVariable userId: Int, @RequestBody @Valid creditCard: CreditCard) {
        clientRepository.findById(userId)
                .map { client ->
                    if(client.id == creditCard.userId) {
                        creditCardRepository.save(creditCard)
                        client.creditCard?.add(creditCard)
                        clientRepository.save(client)
                    } else {
                        throw CustomException()
                    }
                }.orElseThrow { ResponseStatusException(NOT_FOUND) }
    }
}
