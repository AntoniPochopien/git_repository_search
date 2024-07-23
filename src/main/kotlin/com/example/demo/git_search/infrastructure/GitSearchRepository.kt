package com.example.demo.git_search.infrastructure

import com.example.demo.git_search.domain.interfaces.IGitSearchRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component

@Component
class GitSearchRepository: IGitSearchRepository {
    override fun getUserRepositories(username: String) = runBlocking {
        val client = HttpClient(CIO)
        try {
              val response = client.get{
                  url("https://api.github.com/repos/$username")
                  header("Accept", "application/vnd.github+json")
                  header("Authorization","Bearer ")
            }
            println(response.bodyAsText())
        } catch (e: ClientRequestException) {
        println("da")
        } finally {
            client.close()
        }
    }
}