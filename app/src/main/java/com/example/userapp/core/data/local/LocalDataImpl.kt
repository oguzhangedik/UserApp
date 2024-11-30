package com.example.userapp.core.data.local

import android.content.Context
import com.example.userapp.core.data.Result
import com.example.userapp.core.data.dto.error.ErrorMapper
import com.example.userapp.core.data.dto.login.LoginRequest
import com.example.userapp.core.data.dto.login.LoginResponse
import com.example.userapp.core.data.dto.login.isExist
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserDetail
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.data.room.dao.GithubUserDao
import com.example.userapp.core.data.room.dao.GithubUserDetailDao
import com.example.userapp.core.data.room.dao.GithubUserSearchRequestDao
import com.example.userapp.core.data.room.dao.UserDao
import javax.inject.Inject

class LocalDataImpl @Inject constructor(
    val context: Context, val userDao: UserDao,
    val githubUserSearchRequestDao: GithubUserSearchRequestDao,
    val githubUserDao: GithubUserDao,
    val githubUserDetailDao: GithubUserDetailDao
) : LocalData {

    override suspend fun insertGithubUserSearchRequest(request: GithubUserSearchRequest) : Long{
       return githubUserSearchRequestDao.insert(request)
    }

    override suspend fun getAllGithubUserSearchRequests(): List<GithubUserSearchRequest> {
        return githubUserSearchRequestDao.getAll()
    }

    override suspend fun deleteGithubUserSearchRequest(request: GithubUserSearchRequest) {
        githubUserSearchRequestDao.delete(request)
    }

    override suspend fun getByUserSearchRequestParams(request: GithubUserSearchRequest): List<GithubUserSearchRequest> {
        return githubUserSearchRequestDao.getByUserSearchRequestParams(
            textInUserNameToSearch = request.textInUserNameToSearch,
            page = request.page,
            perPageUserCount = request.perPageUserCount
        )
    }

    /************************************************************************************/

    override suspend fun insertGithubUser(githubUser: GithubUser) {
        githubUserDao.insert(githubUser)
    }

    override suspend fun getAllGithubUsers(): List<GithubUser> {
        return githubUserDao.getAll()
    }

    override suspend fun deleteGithubUser(githubUser: GithubUser) {
        githubUserDao.delete(githubUser)
    }

    override suspend fun getGithubUsersBySearchRequestDbId(searchRequestDbId: Long): List<GithubUser> {
        return githubUserDao.getBySearchRequestDbId(searchRequestDbId)
    }

    override suspend fun insertGithubUsers(githubUsers: List<GithubUser>) : List<Long> {
        return githubUserDao.insertGithubUsers(githubUsers)
    }

    override suspend fun updateGithubUser(githubUser: GithubUser) {
        githubUserDao.update(githubUser)
    }

    /************************************************************************************/

    override suspend fun getGithubUserDetailByGithubUserId(githubUser: GithubUser): GithubUserDetail? {
        return githubUserDetailDao.getByGithubUserId(githubUser.id ?: 0)
    }

    override suspend fun insertGithubUserDetail(githubUserDetail: GithubUserDetail) : Long {
        return githubUserDetailDao.insert(githubUserDetail)
    }
}
