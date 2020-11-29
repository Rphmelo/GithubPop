package com.rphmelo.domain.entities

data class RepoPullRequest(
    val id: Int,
    val title: String,
    val body: String,
    val state: String,
    val user: GitHubUser
)