package com.github.disterru.linksshortener

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LinksShortenerApplication

fun main(args: Array<String>) {
    runApplication<LinksShortenerApplication>(*args)
}