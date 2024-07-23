package com.example.demo.git_search.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Commit(
    val sha:String
)
