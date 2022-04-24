package ru.potatophobe.restmock.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.potatophobe.restmock.model.MockResponse
import ru.potatophobe.restmock.model.Request
import ru.potatophobe.restmock.service.RestMockService

@WebMvcTest(RestMockController::class)
class RestMockControllerTest {
    private val path = "/request-path"
    private val requestHeader = Pair("request-header", "request-header-value")
    private val requestParam = Pair("request-param", "request-param-value")
    private val requestBody = "request-body"
    private val requestContentLength = Pair("Content-Length", "12")

    private val responseHeader = Pair("response-header", "response-header-value")
    private val responseBody = "response-body"

    @MockkBean
    lateinit var restMockService: RestMockService

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `control ok`() {
        val request = Request(
            path = path,
            method = HttpMethod.POST,
            headers = mapOf(requestHeader, requestContentLength),
            params = mapOf(requestParam),
            body = requestBody
        )
        val expectedResponse = MockResponse(
            status = HttpStatus.OK,
            headers = mapOf(responseHeader),
            body = responseBody
        )

        every { restMockService.getResponse(eq(request)) } returns expectedResponse

        mockMvc
            .perform(
                post(request.path)
                    .header(requestHeader.first, requestHeader.second)
                    .param(requestParam.first, requestParam.second)
                    .content(requestBody)
            )
            .andExpect(status().isOk)
            .andExpect(header().string(responseHeader.first, responseHeader.second))
            .andExpect(content().string(responseBody))
    }
}