package com.example.demo.git_search.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Branch(
    val name:String,
    val commit: Commit
)
