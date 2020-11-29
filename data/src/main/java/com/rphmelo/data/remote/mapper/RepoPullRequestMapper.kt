package com.rphmelo.data.remote.mapper

import com.rphmelo.data.remote.model.GitHubUserPayload
import com.rphmelo.data.remote.model.RepoPullRequestPayload
import com.rphmelo.domain.entities.GitHubUser
import com.rphmelo.domain.entities.RepoPullRequest

class RepoPullRequestMapper {

    fun map(dataList: List<RepoPullRequestPayload>) = dataList.map { map(it) }

    private fun map(payload: RepoPullRequestPayload) = RepoPullRequest(
        id = payload.id,
        title = payload.title,
        body = payload.body,
        state = payload.state,
        user = map(payload.user)
    )

    fun mapRepoToRemote(jobs: List<RepoPullRequest>) = jobs.map { map(it) }

    private fun map(payload: GitHubUserPayload) = GitHubUser(
        id = payload.id,
        avatarUrl = payload.avatarUrl,
        login = payload.login
    )

    private fun map(githubUser: GitHubUser) = GitHubUserPayload(
        id = githubUser.id,
        avatarUrl = githubUser.avatarUrl,
        login = githubUser.login
    )

    private fun map(pullRequest: RepoPullRequest) = RepoPullRequestPayload(
        id = pullRequest.id,
        title = pullRequest.title,
        body = pullRequest.body,
        state = pullRequest.state,
        user = map(pullRequest.user)
    )
}