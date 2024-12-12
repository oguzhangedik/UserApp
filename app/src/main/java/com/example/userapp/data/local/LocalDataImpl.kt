package com.example.userapp.data.local

import android.content.Context
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.GithubUserDetail
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.data.room.dao.GithubUserDao
import com.example.userapp.data.room.dao.GithubUserDetailDao
import com.example.userapp.data.room.dao.GithubUserSearchRequestDao
import javax.inject.Inject

class LocalDataImpl @Inject constructor(
    val context: Context,
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
