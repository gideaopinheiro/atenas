package com.atenas

import com.atenas.rest.controller.FileUploadController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class AtenasApplication

fun main(args: Array<String>) {
    File(FileUploadController.uploadDirectory).mkdir()
    runApplication<AtenasApplication>(*args)
}
