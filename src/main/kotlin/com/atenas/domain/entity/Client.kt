package com.atenas.domain.entity

import lombok.Data
import java.time.LocalDate
import javax.persistence.*

@Data
@Entity
@Table
data class Client (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column
        var id: Int,

        @Column(name = "profileImg")
        var profileImg: String?,

        @Column(name = "name")
        var name: String,

        @Column(name = "email")
        var email: String,

        @Column(name = "contact")
        var contactNumber: String,

        @Column(name = "cpf")
        var cpf: String,

        @Column(name = "born_date")
        var bornDate: LocalDate,

        @Column(name = "creditCard")
        @OneToMany
        var creditCard: MutableList<CreditCard>?
)