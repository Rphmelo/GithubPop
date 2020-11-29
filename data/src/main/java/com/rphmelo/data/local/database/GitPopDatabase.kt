package com.rphmelo.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rphmelo.data.local.model.RepoLocal
import com.rphmelo.data.local.model.RepoPullRequestLocal

@Database(version = 1, exportSchema = false, entities = [RepoLocal::class, RepoPullRequestLocal::class])
abstract class GitPopDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
    abstract fun repoPullRequestDao(): RepoPullRequestDao
}