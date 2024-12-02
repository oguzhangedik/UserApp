package com.example.userapp.presentation.fragment.userdetail.domain

import com.example.userapp.domain.model.BaseListItemOfGithubUserDetail
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.GithubUserDetail

interface  UserDetailMapper {
    fun map(
        githubUser: GithubUser,
        githubUserDetail: GithubUserDetail
    ): ArrayList<BaseListItemOfGithubUserDetail>
}
