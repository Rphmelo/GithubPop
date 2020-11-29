package com.rphmelo.data.remote.mapper

import com.rphmelo.data.remote.model.GitHubUserPayload
import com.rphmelo.data.remote.model.RepoPayload
import com.rphmelo.domain.entities.GitHubUser
import com.rphmelo.domain.entities.Repo

class RepoMapper {

    fun map(dataList: List<RepoPayload>) = dataList.map { map(it) }

    private fun map(repoPayload: RepoPayload) = Repo(
        id = repoPayload.id,
        name = repoPayload.name,
        fullName = repoPayload.fullName,
        owner = map(repoPayload.owner),
        description = repoPayload.description,
        forksCount = repoPayload.forksCount,
        starsCount = repoPayload.starsCount
    )

    fun mapRepoToRemote(jobs: List<Repo>) = jobs.map { map(it) }

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

    private fun map(repo: Repo) = RepoPayload(
        id = repo.id,
        name = repo.name,
        fullName = repo.fullName,
        owner = map(repo.owner),
        description = repo.description,
        forksCount = repo.forksCount,
        starsCount = repo.starsCount
    )
}