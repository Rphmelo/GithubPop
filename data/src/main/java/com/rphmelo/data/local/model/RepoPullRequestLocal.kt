package com.rphmelo.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rphmelo.domain.entities.GitHubUser

@Entity(tableName = "repoPullRequest")
data class RepoPullRequestLocal(
    @PrimaryKey var id: Int = 0,
    var title: String,
    var body: String,
    var state: String,
    @Embedded(prefix = "userPullRequest") var user: GitHubUser
)