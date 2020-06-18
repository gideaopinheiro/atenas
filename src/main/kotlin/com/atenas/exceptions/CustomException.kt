package com.atenas.exceptions

class CustomException(message: String) : RuntimeException() {
    override val message: String?
        get() = super.message
}
