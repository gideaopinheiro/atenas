package com.atenas.domain.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum class TypeCard {
    @JsonProperty("CREDIT")
    CREDIT,
    @JsonProperty("DEBIT")
    DEBIT
}
