package com.github.disterru.linksshortener.service

import com.github.disterru.linksshortener.domain.Link

interface LinkService {
    suspend fun convertToLink(original: String): Link
    suspend fun getOriginal(shortUrl: String): String
}