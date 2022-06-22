package com.github.disterru.linksshortener.repository

interface LinkRepository {
    suspend fun find(shorten: String): String
    suspend fun save(shorten: String, original: String): Boolean
}