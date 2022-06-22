package com.github.disterru.linksshortener.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class LinkNotFoundException(link: String) : RuntimeException("Link $link not found")