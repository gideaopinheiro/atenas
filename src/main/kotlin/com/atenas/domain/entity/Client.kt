package com.atenas.domain.entity

import lombok.Data
import org.hibernate.validator.constraints.br.CPF
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
@Table
data class Client(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column
        var id: Int,

        @Column(name = "profileImg")
        var profilePicture: String?,

        @Column(name = "name")
        @NotEmpty(message = "name field is required")
        var name: String,

        @Column(name = "email")
        @NotEmpty(message = "email field is required")
        var email: String,

        @Column(name = "contact")
        @NotEmpty(message = "contact number is required")
        var contactNumber: String,

        @Column(name = "cpf")
        @NotEmpty(message = "cpf field is required")
        @CPF(message = "cpf not valid")
        var cpf: String,

        @Column(name = "address")
        @NotEmpty(message = "address field is required")
        var address: String,

        @Column(name = "born_date")
        @NotEmpty(message = "born date field is required")
        var bornDate: LocalDate,

        @Column(name = "creditCard")
        @OneToMany
        var creditCard: MutableList<CreditCard>?
)