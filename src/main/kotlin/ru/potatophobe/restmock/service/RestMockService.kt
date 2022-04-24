package ru.potatophobe.restmock.service

import org.springframework.stereotype.Service
import ru.potatophobe.restmock.model.MockResponse
import ru.potatophobe.restmock.model.Request
import ru.potatophobe.restmock.repository.MockingRequestInMemoryRepository

@Service
class RestMockService(
    private val mockingRequestInMemoryRepository: MockingRequestInMemoryRepository
) {
    fun getResponse(request: Request): MockResponse {
        mockingRequestInMemoryRepository.findAllByPathAndMethod(request.path, request.method)
    }
}