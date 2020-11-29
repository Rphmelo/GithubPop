package com.rphmelo.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.rphmelo.data.local.model.RepoPullRequestLocal
import io.reactivex.Observable

@Dao
interface RepoPullRequestDao {

    @Query("SELECT * FROM repoPullRequest")
    fun getRepoPullRequests(): Observable<List<RepoPullRequestLocal>>

    @Transaction
    fun updateData(users: List<RepoPullRequestLocal>) {
        deleteAll()
        insertAll(users)
    }

    @Insert
    fun insertAll(users: List<RepoPullRequestLocal>)

    @Query("DELETE FROM repoPullRequest")
    fun deleteAll()
}