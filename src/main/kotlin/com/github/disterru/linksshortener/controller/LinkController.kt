package com.github.disterru.linksshortener.controller

import com.github.disterru.linksshortener.service.LinkService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class LinkController(
    private val service: LinkService
) {

    @GetMapping("/shortener/{original}")
    suspend fun convertLink(@PathVariable original: String) = service.convertToLink(original)

    @GetMapping("/{shorten}")
    suspend fun redirect(@PathVariable shorten: String) =
        ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
            .location(URI.create("https://${service.getOriginal(shorten)}"))
            .build<Unit>()
}