package com.example.userapp.ui.usersearch.domain

import com.example.userapp.core.data.dto.user.BaseListItemOfGithubUser
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.platform.viewmodel.AppViewState
import com.example.userapp.model.UiState


data class UserSearchViewState(
    val userSearchActionState: UserSearchActionState = UserSearchActionState.NULL,

    val githubUserSearchRequest: GithubUserSearchRequest? = null,
    val githubUsers : ArrayList<BaseListItemOfGithubUser>? = null,

    override val uiState: UiState = UiState.SUCCESS
) : AppViewState
