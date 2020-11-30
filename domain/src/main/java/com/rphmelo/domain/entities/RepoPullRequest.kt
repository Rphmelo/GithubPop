package com.rphmelo.domain.entities

data class RepoPullRequest(
    var id: Int? = null,
    var title: String? = null,
    var body: String? = null,
    var state: String? = null,
    var user: GitHubUser? = null
)