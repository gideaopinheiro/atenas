package com.atenas.domain.entity

import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
@Table
data class Guy(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int,

        @Column
        @NotEmpty(message = "login is required")
        var login: String,

        @Column
        @NotEmpty(message = "password is required")
        var password: String,

        @Column
        var admin: Boolean = false
) {
        fun isAdmin(): Boolean = admin
}
