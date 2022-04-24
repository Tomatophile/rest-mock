package ru.potatophobe.restmock.repository

import org.springframework.http.HttpMethod
import org.springframework.stereotype.Repository
import ru.potatophobe.restmock.config.properties.MockConfigurationProperties
import ru.potatophobe.restmock.model.MockingRequest

@Repository
class MockingRequestInMemoryRepository(mockConfigurationProperties: MockConfigurationProperties) {
    val mockingRequests = mutableListOf<MockingRequest>()
        .apply { mockConfigurationProperties.requests?.also { addAll(it) } }

    fun findAll() = mockingRequests.toList()

    fun findAllByPathAndMethod(path: String, method: HttpMethod) =
        mockingRequests.filter { it.path == path && it.method == method }

    fun saveAll(values: Collection<MockingRequest>) = mockingRequests.addAll(values)

    fun save(value: MockingRequest) = mockingRequests.add(value)
}