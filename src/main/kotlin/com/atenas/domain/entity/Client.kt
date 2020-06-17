package com.atenas.domain.entity

import lombok.AllArgsConstructor
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor
import java.text.DateFormat
import java.time.LocalDate
import javax.persistence.*

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
data class Client(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column
        private var id: Int,

        @Column(name = "name")
        private var name: String,

        @Column(name = "email")
        private var email: String,

        @Column(name = "contact")
        private var contactNumber: String,

        @Column(name = "cpf")
        private var cpf: String,

        @Column(name = "born_date")
        private var bornDate: LocalDate
) {
        fun getId() = id
        fun getName() = name
        fun getEmail() = email
        fun getContactNumber() = contactNumber
        fun getCpf() = cpf
        fun getBornDate() = bornDate

        fun setId(id: Int) {
                this.id = id
        }

        fun setName(name: String) {
                this.name = name
        }

        fun setEmail(email: String) {
                this.email = email
        }

        fun serContactNumber(contactNumber: String) {
                this.contactNumber = contactNumber
        }

        fun setCpf(cpf: String) {
                this.cpf = cpf
        }

        fun setBornDate(bornDate: LocalDate) {
                this.bornDate = bornDate
        }
}