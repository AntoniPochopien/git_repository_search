package com.example.demo.git_search.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    val id:Long,
    val name: String,
    val owner: Owner,
    val fork: Boolean,
    val branches:MutableList<Branch> = mutableListOf()
)
