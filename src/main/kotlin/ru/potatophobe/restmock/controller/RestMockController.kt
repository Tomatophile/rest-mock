package ru.potatophobe.restmock.controller

import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.potatophobe.restmock.exception.RestMockException
import ru.potatophobe.restmock.model.Request
import ru.potatophobe.restmock.service.RestMockService
import javax.servlet.http.HttpServletRequest

@RestController
class RestMockController(
    private val restMockService: RestMockService
) {

    @RequestMapping("**")
    fun control(
        request: HttpServletRequest,
        @RequestBody(required = false) body: String?
    ): ResponseEntity<String> = restMockService.getResponse(
        Request(
            request.requestURI,
            HttpMethod.valueOf(request.method),
            request.headerNames.toList().associateWith { request.getHeader(it) },
            request.parameterNames.toList().associateWith { request.getParameter(it) },
            body
        )
    ).let { ResponseEntity.status(it.status).apply { it.headers?.forEach { (h, v) -> header(h, v) } }.body(it.body) }

    @ExceptionHandler(RestMockException::class)
    fun handleNoSuitableMockException(exception: RestMockException) =
        ResponseEntity.status(exception.status).apply { exception.headers.forEach { (h, v) -> header(h, v) } }.body(exception.message)
}