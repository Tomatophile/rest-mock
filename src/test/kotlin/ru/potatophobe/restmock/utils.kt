package ru.potatophobe.restmock

import java.lang.reflect.Proxy

inline fun <reified T> mock() = Proxy.newProxyInstance(T::class.java.classLoader, arrayOf(T::class.java)) { _, _, _ -> null } as T