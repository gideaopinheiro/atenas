package com.atenas.exceptions

import java.lang.RuntimeException

class InvalidPasswordException : RuntimeException() {
    override val message: String?
        get() = "Invalid password"
}