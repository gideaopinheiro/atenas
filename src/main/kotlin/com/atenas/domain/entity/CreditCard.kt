package com.atenas.domain.entity

import com.atenas.domain.enums.TypeCard
import lombok.Data
import java.time.LocalDate
import javax.persistence.*

@Data
@Entity
@Table
data class CreditCard(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int,

        @Column(name = "userId")
        var userId: Int,

        @Column(name = "creditCardNumber")
        var creditCardNumber: String,

        @Column(name = "expirationDate")
        var ccExpirationDate: LocalDate,

        @Column(name = "cvv")
        var ccCVV: String,

        @Column(name = "paymentMethod")
        var typeCard: TypeCard
)