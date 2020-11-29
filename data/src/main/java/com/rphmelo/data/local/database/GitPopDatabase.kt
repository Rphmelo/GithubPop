package com.rphmelo.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rphmelo.data.local.model.RepoLocal
import com.rphmelo.data.local.model.RepoPullRequestLocal

@Database(version = 1, exportSchema = false, entities = [RepoLocal::class, RepoPullRequestLocal::class])
abstract class GitPopDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
    abstract fun repoPullRequestDao(): RepoPullRequestDao

    companion object {
        fun createDataBase(context: Context) : GitPopDatabase {
            return Room
                .databaseBuilder(context, GitPopDatabase::class.java, "GitPop.db")
                .build()
        }
    }
}