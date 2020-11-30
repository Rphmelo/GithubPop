package com.rphmelo.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rphmelo.domain.entities.GitHubUser

@Entity(tableName = "repoPullRequest")
data class RepoPullRequestLocal(
    @PrimaryKey var id: Int? = null,
    var title: String? = null,
    var body: String? = null,
    var state: String? = null,
    @Embedded(prefix = "userPullRequest") var user: GitHubUser? = null
)