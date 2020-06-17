package com.atenas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AtenasApplication

fun main(args: Array<String>) {
    runApplication<AtenasApplication>(*args)
}
