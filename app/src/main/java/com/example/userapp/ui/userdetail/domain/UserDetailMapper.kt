package com.example.userapp.ui.userdetail.domain

import com.example.userapp.core.data.dto.user.BaseListItemOfGithubUserDetail
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserDetail

interface  UserDetailMapper {
    fun map(
        githubUser: GithubUser,
        githubUserDetail: GithubUserDetail
    ): ArrayList<BaseListItemOfGithubUserDetail>
}
