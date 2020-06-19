package com.atenas.rest.controller

import com.atenas.domain.entity.Client
import com.atenas.domain.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/clients")
class ClientController (@Autowired private val clientRepository: ClientRepository) {
    @GetMapping
    fun getAllClients(): List<Client> {
        return clientRepository.findAll()
    }

    @GetMapping("/{id}")
    fun getClientById(@PathVariable id: Int): Client {
        return clientRepository
                .findById(id)
                .orElseThrow{ ResponseStatusException(NOT_FOUND) }
    }

    @PostMapping
    @ResponseStatus(CREATED)
    fun saveClient(@RequestBody @Valid client: Client): Client {
        return clientRepository.save(client)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    fun deleteClient(@PathVariable id: Int) {
        clientRepository.findById(id).map { client -> clientRepository.delete(client) }
                .orElseThrow { ResponseStatusException(NOT_FOUND) }
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    fun updateClientById(
            @PathVariable id: Int,
            @RequestBody client: Client
    ) : Client {
        return clientRepository.findById(id)
                .map { c ->
                    c.name = client.name
                    c.email = client.email
                    c.contactNumber = client.contactNumber
                    c.cpf = client.cpf
                    c.bornDate = client.bornDate
                    clientRepository.save(c)
                }.orElseGet {
                    client.id = id
                    clientRepository.save(client)
                }
    }

}
