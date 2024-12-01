package com.example.userapp.ui.usersearch.domain

import com.example.userapp.core.data.dto.user.BaseListItemOfGithubUser
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.platform.viewmodel.AppViewState
import com.example.userapp.core.utils.*


data class UserSearchViewState(
    val userSearchActionState: UserSearchActionState = UserSearchActionState.NULL,

    var searchText : String = EMPTY,

    val githubUserSearchRequest: GithubUserSearchRequest? = null,
    val githubUsers : ArrayList<BaseListItemOfGithubUser>? = null,

    val lastFavoriteUpdateGithubUser : GithubUser? = null,

    ) : AppViewState
