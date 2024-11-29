package com.example.userapp.ui.userdetail.domain


import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserDetail
import com.example.userapp.core.platform.viewmodel.AppViewState
import com.example.userapp.model.UiState


data class UserDetailViewState(
    val userDetailActionState: UserDetailActionState = UserDetailActionState.NULL,

    val githubUser : GithubUser? = null,
    val githubUserDetail : GithubUserDetail? = null,

    override val uiState: UiState = UiState.SUCCESS
) : AppViewState
