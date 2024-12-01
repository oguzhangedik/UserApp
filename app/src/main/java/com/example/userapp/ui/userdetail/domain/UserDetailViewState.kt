package com.example.userapp.ui.userdetail.domain


import com.example.userapp.core.data.dto.user.BaseListItemOfGithubUserDetail
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserDetail
import com.example.userapp.core.platform.viewmodel.AppViewState


data class UserDetailViewState(
    val userDetailActionState: UserDetailActionState = UserDetailActionState.NULL,

    val githubUser : GithubUser? = null,
    val githubUserDetail : GithubUserDetail? = null,

    val userDetails: ArrayList<BaseListItemOfGithubUserDetail>? = null,

) : AppViewState
