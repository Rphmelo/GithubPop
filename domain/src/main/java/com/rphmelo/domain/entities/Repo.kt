package com.rphmelo.domain.entities

data class Repo(
    val id: Int,
    val name: String,
    val fullName: String,
    val owner: GitHubUser,
    val description: String,
    val forksCount: Int,
    val starsCount: Int
)