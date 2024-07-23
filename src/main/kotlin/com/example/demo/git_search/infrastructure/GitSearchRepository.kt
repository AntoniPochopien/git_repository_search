package com.example.demo.git_search.infrastructure

import com.example.demo.exceptions.exceptions.GhUserNotFoundException
import com.example.demo.git_search.domain.interfaces.IGitSearchRepository
import com.example.demo.git_search.domain.models.Branch
import com.example.demo.git_search.domain.models.Repository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.header
import io.ktor.client.request.url
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Component
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

@Component
class GitSearchRepository: IGitSearchRepository {
     private val ghToken:String = System.getenv("GH_AUTH_TOKEN")

    override fun getUserRepositories(username: String):  List<Repository> = runBlocking {
        val client = HttpClient(CIO){
            install(ContentNegotiation){
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        try {
              val repositoriesResponse  = client.get{
                  url("https://api.github.com/users/$username/repos")
                  header("Accept", "application/vnd.github+json")
                  header("Authorization","Bearer $ghToken")
            }

            if(repositoriesResponse.status == HttpStatusCode.NotFound){
                throw GhUserNotFoundException()
            }

            val repositories:List<Repository> = repositoriesResponse.body()
            println("getUserRepositories raw repositories success")

            val nonForkedRepositories = repositories.filter { !it.fork }

             val reposWithBranches: List<Repository> =  nonForkedRepositories.map {
                val repoBranches:List<Branch> = client.get{
                    url("https://api.github.com/repos/$username/${it.name}/branches")
                    header("Accept", "application/vnd.github+json")
                    header("Authorization","Bearer $ghToken")
                }.body()
                it.branches.addAll(repoBranches)
                 it
            }
            println("getUserRepositories branches success")
            return@runBlocking reposWithBranches
        } catch (e: ClientRequestException) {
            println("getUserRepositories unexpectedError: ${e.response.bodyAsText()}")
            throw Exception("Unexpected error: ${e.response.bodyAsText()}")
        }finally {
            client.close()
        }
    }
}