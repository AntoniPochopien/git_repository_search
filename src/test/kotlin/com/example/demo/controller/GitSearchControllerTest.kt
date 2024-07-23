package com.example.demo.controller

import com.example.demo.git_search.application.GitSearchService
import com.example.demo.git_search.domain.models.Owner
import com.example.demo.git_search.domain.models.Repository
import com.example.demo.git_search.presentation.controllers.GitSearchController
import com.example.demo.git_search.presentation.dto.GitSearchResponseDto
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.Test
import kotlin.test.assertEquals

@WebMvcTest(controllers = [GitSearchController::class])
@AutoConfigureWebTestClient
class GitSearchControllerTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var gitSearchServiceMock : GitSearchService

    @Test
    fun getGhUserRepositories() {
        every { gitSearchServiceMock.getUserRepositories("test") } returns listOf(
            Repository(id = 0, branches = mutableListOf(), name = "repo name", fork = true, owner = Owner(login = "FunnyName")),
            Repository(id = 1, branches = mutableListOf(), name = "repo name", fork = true, owner = Owner(login = "FunnyName")),
            Repository(id = 2, branches = mutableListOf(), name = "repo name", fork = true, owner = Owner(login = "FunnyName"))
        )

        val response = webTestClient.get().uri("/GitSearch?username=test")
            .exchange()
            .expectStatus().isOk
            .expectBodyList(GitSearchResponseDto::class.java)
            .returnResult()

        val gitSearchResponseDtos = response.responseBody
        assertEquals(3, gitSearchResponseDtos?.size)
    }
}