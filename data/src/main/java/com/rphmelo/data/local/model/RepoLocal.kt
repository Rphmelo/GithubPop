package com.rphmelo.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rphmelo.domain.entities.GitHubUser

@Entity(tableName = "repos")
data class RepoLocal(
    @PrimaryKey var id: Int = 0,
    var name: String,
    var fullName: String,
    @Embedded var owner: GitHubUser,
    var description: String,
    var forksCount: Int,
    var starsCount: Int
)