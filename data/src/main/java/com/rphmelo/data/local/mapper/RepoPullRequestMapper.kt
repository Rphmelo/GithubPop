package com.rphmelo.data.local.mapper

import com.rphmelo.data.local.model.RepoPullRequestLocal
import com.rphmelo.domain.entities.RepoPullRequest

class RepoPullRequestMapper {

    fun map(localData: List<RepoPullRequestLocal>) = localData.map { map(it) }

    private fun map(local: RepoPullRequestLocal) = RepoPullRequest(
        id = local.id,
        title = local.title,
        body = local.body,
        state = local.state,
        user = local.user
    )

    fun mapRepoToCache(jobs: List<RepoPullRequest>) = jobs.map { map(it) }

    private fun map(pullRequest: RepoPullRequest) = RepoPullRequestLocal(
        id = pullRequest.id,
        title = pullRequest.title,
        body = pullRequest.body,
        state = pullRequest.state,
        user = pullRequest.user
    )
}