package com.example.userapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.GithubUserDetail
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.data.room.BaseDao

@Dao
abstract class GithubUserSearchRequestDao  : BaseDao<GithubUserSearchRequest>() {
    @Query("SELECT * FROM GithubUserSearchRequest")
    abstract suspend fun getAll(): List<GithubUserSearchRequest>

    @Query("SELECT * FROM GithubUserSearchRequest WHERE dbId = :dbId")
    abstract suspend fun getById(dbId: Long): GithubUserSearchRequest?

    @Query("SELECT * FROM GithubUserSearchRequest WHERE textInUserNameToSearch = :textInUserNameToSearch AND page = :page AND perPageUserCount = :perPageUserCount")
    abstract suspend fun getByUserSearchRequestParams(textInUserNameToSearch: String, page: Int, perPageUserCount: Int): List<GithubUserSearchRequest>
}

@Dao
abstract class GithubUserDao  : BaseDao<GithubUser>() {
    @Query("SELECT * FROM GithubUser")
    abstract suspend fun getAll(): List<GithubUser>

    @Query("SELECT * FROM GithubUser WHERE dbId = :dbId")
    abstract suspend fun getById(dbId: Long): GithubUser?

    @Query("SELECT * FROM GithubUser WHERE searchRequestDbId = :searchRequestDbId")
    abstract suspend fun getBySearchRequestDbId(searchRequestDbId: Long): List<GithubUser>

    @Insert
    abstract suspend fun insertGithubUsers(githubUsers: List<GithubUser>) : List<Long>
}

@Dao
abstract class GithubUserDetailDao  : BaseDao<GithubUserDetail>() {
    @Query("SELECT * FROM GithubUserDetail WHERE id = :id")
    abstract suspend fun getByGithubUserId(id: Int): GithubUserDetail?
}