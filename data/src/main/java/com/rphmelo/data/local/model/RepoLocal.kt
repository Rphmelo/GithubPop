package com.rphmelo.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rphmelo.domain.entities.GitHubUser

@Entity(tableName = "repos")
data class RepoLocal(
    @PrimaryKey var id: Long? = null,
    var name: String? = null,
    var fullName: String? = null,
    @Embedded(prefix = "repoOwner") var owner: GitHubUser? = null,
    var description: String? = null,
    var forksCount: Int? = null,
    var starsCount: Int? = null
)