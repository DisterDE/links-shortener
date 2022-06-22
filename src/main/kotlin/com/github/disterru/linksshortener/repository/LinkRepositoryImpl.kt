package com.github.disterru.linksshortener.repository

import com.github.disterru.linksshortener.exception.LinkNotFoundException
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.ReactiveValueOperations
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class LinkRepositoryImpl(template: ReactiveRedisTemplate<String, String>) : LinkRepository {
    private val redisOps: ReactiveValueOperations<String, String>

    init {
        redisOps = template.opsForValue()
    }

    override suspend fun find(shorten: String): String {
        return redisOps.getAndExpire(shorten, Duration.ofHours(1))
            .awaitSingleOrNull()
            ?: throw LinkNotFoundException(shorten)
    }


    override suspend fun save(shorten: String, original: String): Boolean {
        return redisOps.set(shorten, original, Duration.ofHours(1))
            .awaitSingle()
    }
}