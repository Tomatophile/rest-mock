package ru.potatophobe.restmock.service

import org.springframework.stereotype.Service
import ru.potatophobe.restmock.exception.NoSuitableMockException
import ru.potatophobe.restmock.model.MockResponse
import ru.potatophobe.restmock.model.MockingRequest
import ru.potatophobe.restmock.model.Request
import ru.potatophobe.restmock.repository.MockingRequestRepository

@Service
class RestMockServiceImpl(
    private val mockingRequestRepository: MockingRequestRepository
) : RestMockService {

    override fun getResponse(request: Request): MockResponse {
        val scoredSuitableMocks = mockingRequestRepository.findAll()
            .filter { isSuitableMock(request, it) }
            .associateBy({ scoreMock(request, it) }, { it })
        if (scoredSuitableMocks.isEmpty()) throw NoSuitableMockException()

        return scoredSuitableMocks.toSortedMap { a, b -> a.compareTo(b) }.let { it[it.firstKey()] }?.response ?: throw NoSuitableMockException()
    }

    private fun scoreMock(request: Request, mockingRequest: MockingRequest): Int {
        if (!isSuitableMock(request, mockingRequest)) return 0
        var score = 10

        if (request.path == mockingRequest.path) score += 10
        if (request.method == mockingRequest.method) score += 10
        if (request.headers == mockingRequest.headers) score += 10
        if (request.params == mockingRequest.params) score += 10
        if (request.body == mockingRequest.body) score += 10

        return score
    }

    private fun isSuitableMock(request: Request, mockingRequest: MockingRequest): Boolean {
        mockingRequest.path.let { if (!matchesUriPattern(request.path, mockingRequest.path)) return false }
        mockingRequest.method?.let { if (request.method != it) return false }

        if (
            (!mockingRequest.allowAnyHeaders && mockingRequest.headers != request.headers)
            || (!mockingRequest.allowAnyParams && mockingRequest.params != request.params)
            || (!mockingRequest.allowAnyBody && mockingRequest.body != request.body)
        ) {
            return false
        }

        mockingRequest.headers?.forEach { (h, v) -> if (request.headers?.containsKey(h) != true || request.headers[h] != v) return false }
        mockingRequest.params?.forEach { (p, v) -> if (request.params?.containsKey(p) != true || request.params[p] != v) return false }

        return true
    }
}