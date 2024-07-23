package com.example.demo.exceptions.exceptions

data class GhUserNotFoundException(
    override val message: String = "Github user not found"
) : RuntimeException(message)