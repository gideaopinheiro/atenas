package com.atenas.domain.entity

import com.atenas.domain.enums.TypeCard
import lombok.Data
import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Data
@Entity
@Table
data class CreditCard(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int,

        @Column(name = "userId")
        @NotEmpty(message = "user id field is required")
        var userId: Int,

        @Column(name = "creditCardNumber")
        @NotEmpty(message = "credit card number is required")
        var creditCardNumber: String,

        @Column(name = "expirationDate")
        @NotEmpty(message = "expiration date is required")
        var ccExpirationDate: LocalDate,

        @Column(name = "cvv")
        @NotEmpty(message = "CVV is required")
        var ccCVV: String,

        @Column(name = "paymentMethod")
        @NotEmpty(message = "type card is required")
        var typeCard: TypeCard
)