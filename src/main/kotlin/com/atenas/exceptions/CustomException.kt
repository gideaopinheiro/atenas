package com.atenas.exceptions

class CustomException : RuntimeException() {
    override val message: String?
        get() = super.message
}
