package com.boy.githubuserviewer

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {
    @GET("search/users")
    suspend fun searchUsers(@Query("q") query: String): UserSearchResponse

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): GitHubUser

    @GET("users/{username}/repos")
    suspend fun getUserRepos(@Path("username") username: String): List<GitHubRepo>
}