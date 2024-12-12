package com.example.userapp.presentation.fragment.userdetail.domain


import com.example.userapp.domain.model.BaseListItemOfGithubUserDetail
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.GithubUserDetail
import com.example.userapp.core.base.viewmodel.AppViewState


data class UserDetailViewState(
    val userDetailActionState: UserDetailActionState = UserDetailActionState.NULL,

    val githubUser : GithubUser? = null,
    val githubUserDetail : GithubUserDetail? = null,

    val userDetails: ArrayList<BaseListItemOfGithubUserDetail>? = null,

    ) : AppViewState
