package com.rphmelo.domain.entities

data class Repo(
    var id: Long? = null,
    var name: String? = null,
    var fullName: String? = null,
    var owner: GitHubUser? = null,
    var description: String? = null,
    var forksCount: Int? = null,
    var starsCount: Int? = null
)