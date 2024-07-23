package com.example.demo.git_search.presentation.controllers

import com.example.demo.git_search.application.GitSearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/GitSearch")
class GitSearchController(private val gitSearchService: GitSearchService) {

    @GetMapping
    fun getUserRepositories(@RequestParam username: String){
        gitSearchService.getUserRepositories(username)
    }

}