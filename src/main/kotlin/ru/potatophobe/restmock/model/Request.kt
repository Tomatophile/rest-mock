package ru.potatophobe.restmock.model

import org.springframework.http.HttpMethod

data class Request(
    val path: String,
    val method: HttpMethod,
    val headers: Map<String, String>?,
    val params: Map<String, String>?,
    val body: String?
)
