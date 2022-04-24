package ru.potatophobe.restmock.service

import ru.potatophobe.restmock.model.MockResponse
import ru.potatophobe.restmock.model.Request

interface RestMockService {

    fun getResponse(request: Request): MockResponse
}