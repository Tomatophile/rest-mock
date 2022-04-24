package ru.potatophobe.restmock.repository

import org.springframework.stereotype.Repository
import ru.potatophobe.restmock.config.properties.MockConfigurationProperties
import ru.potatophobe.restmock.model.MockingRequest

@Repository
class MockingRequestInMemoryRepository(
    mockConfigurationProperties: MockConfigurationProperties
) : MockingRequestRepository {
    private val mockingRequests = mutableListOf<MockingRequest>().apply { mockConfigurationProperties.requests?.also { addAll(it) } }

    override fun findAll() = mockingRequests.toList()

    override fun saveAll(values: Collection<MockingRequest>) {
        mockingRequests.addAll(values)
    }
}