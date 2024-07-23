package com.example.demo.git_search.domain.models

data class Repository(
    val name: String,
    val owner: Owner,
    val fork: Boolean
)
