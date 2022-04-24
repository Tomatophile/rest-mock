package ru.potatophobe.restmock.model

import org.springframework.http.HttpMethod

data class MockingRequest(
    val path: String,
    val method: HttpMethod?,
    val headers: Map<String, String>?,
    val allowAnyHeaders: Boolean = true,
    val params: Map<String, String>?,
    val allowAnyParams: Boolean = true,
    val body: String?,
    val allowAnyBody: Boolean = false,
    val response: MockResponse
)