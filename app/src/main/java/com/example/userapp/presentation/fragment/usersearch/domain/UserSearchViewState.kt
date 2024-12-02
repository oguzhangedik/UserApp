package com.example.userapp.presentation.fragment.usersearch.domain

import com.example.userapp.domain.model.BaseListItemOfGithubUser
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.core.base.viewmodel.AppViewState
import com.example.userapp.core.utils.*


data class UserSearchViewState(
    val userSearchActionState: UserSearchActionState = UserSearchActionState.NULL,

    var searchText : String = EMPTY,

    val githubUserSearchRequest: GithubUserSearchRequest? = null,
    val githubUsers : ArrayList<BaseListItemOfGithubUser>? = null,

    val lastFavoriteUpdateGithubUser : GithubUser? = null,

    ) : AppViewState
