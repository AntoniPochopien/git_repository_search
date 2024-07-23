package com.example.demo.git_search.domain.interfaces

import com.example.demo.git_search.domain.models.Repository

interface IGitSearchRepository {
    fun getUserRepositories(username:String):List<Repository>
}