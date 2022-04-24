package ru.potatophobe.restmock.exception

import org.springframework.http.HttpStatus

class NoSuitableMockException : RestMockException(HttpStatus.BAD_REQUEST, "No suitable mock")