package com.rphmelo.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.rphmelo.data.local.model.RepoLocal
import io.reactivex.Observable

@Dao
interface RepoDao {

    @Query("SELECT * FROM repos")
    fun getRepos(): Observable<List<RepoLocal>>

    @Transaction
    fun updateData(users: List<RepoLocal>) {
        deleteAll()
        insertAll(users)
    }

    @Insert
    fun insertAll(users: List<RepoLocal>)

    @Query("DELETE FROM repos")
    fun deleteAll()
}