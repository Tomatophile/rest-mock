package ru.potatophobe.restmock.repository

import ru.potatophobe.restmock.model.MockingRequest

interface MockingRequestRepository {

    fun findAll(): List<MockingRequest>

    fun saveAll(values: Collection<MockingRequest>)
}