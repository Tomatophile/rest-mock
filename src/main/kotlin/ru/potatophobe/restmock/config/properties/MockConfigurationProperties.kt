package ru.potatophobe.restmock.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import ru.potatophobe.restmock.model.MockingRequest

@ConfigurationProperties("mock")
@ConstructorBinding
data class MockConfigurationProperties(
    val requests: List<MockingRequest>?
)
