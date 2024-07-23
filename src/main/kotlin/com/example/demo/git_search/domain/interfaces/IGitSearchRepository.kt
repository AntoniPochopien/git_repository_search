package com.example.demo.git_search.domain.interfaces

interface IGitSearchRepository {
    fun getUserRepositories(username:String)
}