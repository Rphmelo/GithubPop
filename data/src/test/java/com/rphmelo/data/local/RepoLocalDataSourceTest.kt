package com.rphmelo.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rphmelo.data.DataModuleTestApplication
import com.rphmelo.data.local.database.GitPopDatabase
import com.rphmelo.data.local.database.RepoDao
import com.rphmelo.data.local.model.RepoLocal
import com.rphmelo.domain.entities.GitHubUser
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = DataModuleTestApplication::class)
class RepoLocalDataSourceTest {
    private lateinit var repoDao: RepoDao
    private lateinit var db: GitPopDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, GitPopDatabase::class.java).allowMainThreadQueries().build()
        repoDao = db.repoDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun `Given database is seted up When insert data Then should add on database`() {
        val gitHubUser = GitHubUser(11, "https://www.avatar.com", "Rphmelo")
        val repoLocalFake = RepoLocal(1, "GitPop", "GitPop/Rphmelo", gitHubUser, "An app to show repositories.", 20, 12)
        val repoListFake: List<RepoLocal> = arrayListOf(repoLocalFake)

        repoDao.insertAll(repoListFake)

        repoDao.getRepos()
            .subscribeOn(Schedulers.io())
            .subscribe {
                assertEquals(false, it.isEmpty())
                val repoLocal = it[0]
                assertEquals(repoLocalFake.id, repoLocal.id)
                assertEquals(repoLocalFake.name, repoLocal.name)
                assertEquals(repoLocalFake.fullName, repoLocal.fullName)
                assertEquals(repoLocalFake.owner.id, repoLocal.owner.id)
                assertEquals(repoLocalFake.owner.login, repoLocal.owner.login)
                assertEquals(repoLocalFake.owner.avatarUrl, repoLocal.owner.avatarUrl)
                assertEquals(repoLocalFake.description, repoLocal.description)
                assertEquals(repoLocalFake.forksCount, repoLocal.forksCount)
                assertEquals(repoLocalFake.starsCount, repoLocal.starsCount)
            }
    }

    @Test
    fun `Given database is seted up When insert data and delete Then database should not have any data`() {
        val gitHubUser = GitHubUser(11, "https://www.avatar.com", "Rphmelo")
        val repoLocalFake = RepoLocal(1, "GitPop", "GitPop/Rphmelo", gitHubUser, "An app to show repositories.", 20, 12)
        val repoListFake: List<RepoLocal> = arrayListOf(repoLocalFake)

        repoDao.insertAll(repoListFake)

        repoDao.getRepos()
            .subscribeOn(Schedulers.io())
            .subscribe {
                assertEquals(true, it?.isNotEmpty())
                val repoLocal = it[0]
                assertEquals(repoLocalFake.id, repoLocal.id)
                assertEquals(repoLocalFake.name, repoLocal.name)
                assertEquals(repoLocalFake.fullName, repoLocal.fullName)
                assertEquals(repoLocalFake.owner.id, repoLocal.owner.id)
                assertEquals(repoLocalFake.owner.login, repoLocal.owner.login)
                assertEquals(repoLocalFake.owner.avatarUrl, repoLocal.owner.avatarUrl)
                assertEquals(repoLocalFake.description, repoLocal.description)
                assertEquals(repoLocalFake.forksCount, repoLocal.forksCount)
                assertEquals(repoLocalFake.starsCount, repoLocal.starsCount)
            }

            repoDao.deleteAll()

        repoDao.getRepos()
            .subscribeOn(Schedulers.io())
            .subscribe {
                assertEquals(false, it?.isEmpty())
            }
    }
}