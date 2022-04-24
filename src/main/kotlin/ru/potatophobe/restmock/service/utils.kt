package ru.potatophobe.restmock.service

fun matchesUriPattern(uri: String, pattern: String): Boolean {
    val uriIterator = parseUri(uri).iterator()
    val patternIterator = parseUri(pattern).iterator()

    while (patternIterator.hasNext()) {
        var pathPattern = patternIterator.next()
        if (!uriIterator.hasNext()) return false
        var path = uriIterator.next()

        if (pathPattern == "**") {
            while (pathPattern == "**" && patternIterator.hasNext()) {
                pathPattern = patternIterator.next()
            }
            if (pathPattern == "**") return true
            var matches = false
            while (uriIterator.hasNext()) {
                path = uriIterator.next()
                if (path.matches(Regex(pathPattern))) {
                    matches = true
                    break
                }
            }
            if (!matches) return false
        } else if (pathPattern == "*") continue
        else if (!path.matches(Regex(pathPattern))) return false
    }
    return true
}

private fun parseUri(uri: String) = uri.split("/")
    .run { if (first() == "") drop(1) else this }
    .run { if (last() == "") dropLast(1) else this }