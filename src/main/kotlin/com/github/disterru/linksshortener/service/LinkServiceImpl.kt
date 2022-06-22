package com.github.disterru.linksshortener.service

import com.github.disterru.linksshortener.domain.Link
import com.github.disterru.linksshortener.repository.LinkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service

@Service
class LinkServiceImpl(
    private val repository: LinkRepository
) : LinkService {

    override suspend fun convertToLink(original: String) = withContext(Dispatchers.IO) {
        val shorten = createShortLink()
        repository.save(shorten, original)
        Link(original, shorten)
    }

    override suspend fun getOriginal(shortUrl: String) = withContext(Dispatchers.IO) {
        repository.find(shortUrl)
    }

    private fun createShortLink() = RandomStringUtils.randomAlphanumeric(6)
}