package com.rphmelo.data.local.mapper

import com.rphmelo.data.local.model.RepoLocal
import com.rphmelo.domain.entities.Repo

class RepoMapper {

    fun map(localData: List<RepoLocal>) = localData.map { map(it) }

    private fun map(repoLocal: RepoLocal) = Repo(
        id = repoLocal.id,
        name = repoLocal.name,
        fullName = repoLocal.fullName,
        owner = repoLocal.owner,
        description = repoLocal.description,
        forksCount = repoLocal.forksCount,
        starsCount = repoLocal.starsCount
    )

    fun mapRepoToCache(jobs: List<Repo>) = jobs.map { map(it) }

    private fun map(repo: Repo) = RepoLocal(
        id = repo.id,
        name = repo.name,
        fullName = repo.fullName,
        owner = repo.owner,
        description = repo.description,
        forksCount = repo.forksCount,
        starsCount = repo.starsCount
    )
}