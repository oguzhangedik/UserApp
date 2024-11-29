package com.example.userapp.core.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.userapp.core.data.dto.login.LoginResponse
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.data.room.BaseDao

@Dao
abstract class UserDao : BaseDao<LoginResponse>() {
    @Query("SELECT * FROM LoginResponse")
    abstract fun getAll(): List<LoginResponse>
}

@Dao
abstract class GithubUserSearchRequestDao  : BaseDao<GithubUserSearchRequest>() {
    @Query("SELECT * FROM GithubUserSearchRequest")
    abstract suspend fun getAll(): List<GithubUserSearchRequest>

    @Query("SELECT * FROM GithubUserSearchRequest WHERE dbId = :dbId")
    abstract suspend fun getById(dbId: Long): GithubUserSearchRequest?

    // textInUserNameToSearch, page ve perPageUserCount değerleri aynı olan nesneyi almak
    @Query("SELECT * FROM GithubUserSearchRequest WHERE textInUserNameToSearch = :textInUserNameToSearch AND page = :page AND perPageUserCount = :perPageUserCount")
    abstract suspend fun getByUserSearchRequestParams(textInUserNameToSearch: String, page: Int, perPageUserCount: Int): List<GithubUserSearchRequest>
}

@Dao
abstract class GithubUserDao  : BaseDao<GithubUser>() {
    @Query("SELECT * FROM GithubUser")
    abstract suspend fun getAll(): List<GithubUser>

    @Query("SELECT * FROM GithubUser WHERE dbId = :dbId")
    abstract suspend fun getById(dbId: Long): GithubUser?

    // searchRequestDbId ile eşleşen GithubUser öğelerini almak
    @Query("SELECT * FROM GithubUser WHERE searchRequestDbId = :searchRequestDbId")
    abstract suspend fun getBySearchRequestDbId(searchRequestDbId: Long): List<GithubUser>

    // GithubUser liste ekleme (Insert)
    @Insert
    abstract suspend fun insertGithubUsers(githubUsers: List<GithubUser>) : List<Long>
}