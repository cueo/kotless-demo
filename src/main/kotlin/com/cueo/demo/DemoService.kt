package com.cueo.demo

import io.kotless.dsl.lang.http.Get

@Get("/")
fun gettingStartedPage() : String {
    return "Hello"
}
