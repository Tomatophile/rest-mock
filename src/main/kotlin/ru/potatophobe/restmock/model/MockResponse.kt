package ru.potatophobe.restmock.model

import org.springframework.http.HttpStatus

data class MockResponse(
    val status: HttpStatus,
    val headers: Map<String, String>?,
    val body: String?
)