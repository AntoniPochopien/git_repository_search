package com.example.demo.git_search.application

import com.example.demo.git_search.domain.interfaces.IGitSearchRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GitSearchService @Autowired constructor(private val gitSearchRepository: IGitSearchRepository) {
    fun getUserRepositories(username:String)= gitSearchRepository.getUserRepositories(username)
}