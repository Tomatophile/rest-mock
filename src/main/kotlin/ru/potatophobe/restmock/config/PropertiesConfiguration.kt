package ru.potatophobe.restmock.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import ru.potatophobe.restmock.config.properties.MockConfigurationProperties

@Configuration
@EnableConfigurationProperties(
    MockConfigurationProperties::class
)
class PropertiesConfiguration