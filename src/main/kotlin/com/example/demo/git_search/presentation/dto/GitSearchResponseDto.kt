package com.example.demo.git_search.presentation.dto

data class GitSearchResponseDto(
    val repositoryName: String,
    val ownerLogin: String,
    val branches: List<BranchInfoDto>
)
