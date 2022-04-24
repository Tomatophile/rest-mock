package ru.potatophobe.restmock.exception

import org.springframework.http.HttpStatus

open class RestMockException(
    val status: HttpStatus,
    override val message: String,
    val headers: Map<String, String> = mapOf()
) : RuntimeException()