package com.boy.githubuserviewer

import com.google.gson.annotations.SerializedName

data class UserSearchResponse(@SerializedName("items") val items: List<GitHubUserBrief>)
data class GitHubUserBrief(@SerializedName("login") val login: String, @SerializedName("avatar_url") val avatarUrl: String)
data class GitHubUser(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("name") val name: String?,
    @SerializedName("followers") val followers: Int,
    @SerializedName("following") val following: Int
)
data class GitHubRepo(
    @SerializedName("name") val name: String,
    @SerializedName("language") val language: String?,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("description") val description: String?,
    @SerializedName("fork") val isFork: Boolean,
    @SerializedName("html_url") val url: String
)