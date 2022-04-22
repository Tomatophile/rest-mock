package ru.potatophobe.restmock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestMockApplication

fun main(args: Array<String>) {
    runApplication<RestMockApplication>(*args)
}
