package com.atenas.exceptions

import java.lang.RuntimeException

class InvalidTokenException : RuntimeException() {
    override val message: String?
        get() = "Invalid Token"
}
