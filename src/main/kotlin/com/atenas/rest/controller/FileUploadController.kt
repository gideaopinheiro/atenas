package com.atenas.rest.controller

import com.atenas.domain.repository.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1")
class FileUploadController(@Autowired private val clientRepository: ClientRepository) {
    companion object {
        val uploadDirectory = System.getProperty("user.dir") + "/daipe-uploads"
    }

    @PostMapping("/profile-picture/{userId}")
    @ResponseStatus(CREATED)
    fun uploadImage(@RequestParam("file") file: MultipartFile, @PathVariable userId: Int) {
        clientRepository.findById(userId)
                .map { client ->
                    val fileName: String = storeImage(file)
                    client.profilePicture = fileName
                    clientRepository.save(client)
                }.orElseThrow { ResponseStatusException(NOT_FOUND) }
    }

    fun storeImage(file: MultipartFile): String {

        val fileName = StringBuilder()
        val fileNameAndPath = Paths.get(uploadDirectory, file.originalFilename)
        fileName.append("${LocalDateTime.now()}-${file.originalFilename}")

        Files.write(fileNameAndPath, file.bytes)

        return fileName.toString()
    }
}