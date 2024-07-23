package com.example.demo.git_search.presentation.controllers

import com.example.demo.git_search.application.GitSearchService
import com.example.demo.git_search.presentation.dto.BranchInfoDto
import com.example.demo.git_search.presentation.dto.GitSearchResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.function.EntityResponse

@RestController
@RequestMapping("/GitSearch")
class GitSearchController(private val gitSearchService: GitSearchService) {

    @GetMapping
    fun getUserRepositories(@RequestParam username: String): ResponseEntity<List<GitSearchResponseDto>>{
      val repositories =  gitSearchService.getUserRepositories(username)
        return ResponseEntity(
            repositories.map {repository ->
                GitSearchResponseDto(
                    ownerLogin = repository.owner.login,
                    repositoryName = repository.name,
                    branches = repository.branches.map {
                            branch->
                        BranchInfoDto(
                            name = branch.name,
                            lastCommitSha = branch.commit.sha
                        )
                    }
                )
            },
            HttpStatus.OK
        )
        }
    }
