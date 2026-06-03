package io.github.kevinah95.myapplication

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform