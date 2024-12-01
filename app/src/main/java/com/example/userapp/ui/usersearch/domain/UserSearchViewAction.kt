package com.example.userapp.ui.usersearch.domain

import com.example.userapp.core.data.dto.user.BaseListItemOfGithubUser
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.platform.viewmodel.AppViewAction


sealed class UserSearchViewAction : AppViewAction {
    data class OnGithubUsers(
        val githubUserSearchRequest: GithubUserSearchRequest? = null,
        val githubUsers : ArrayList<BaseListItemOfGithubUser>? = null
    ) : UserSearchViewAction()

    data class OnLoadMoreGithubUsers(
        val githubUserSearchRequest: GithubUserSearchRequest? = null,
        val githubUsers : ArrayList<BaseListItemOfGithubUser>? = null
    ) : UserSearchViewAction()

    data class OnSearchNewGithubUsers(val searchText : String
    ) : UserSearchViewAction()

    data class OnGithubUserFavoriteStateUpdate(val githubUser: GithubUser
    ) : UserSearchViewAction()

    data object OnClearAction : UserSearchViewAction()

    data object OnEnableLoadMoreAction : UserSearchViewAction()
    data object OnDisableLoadMoreAction : UserSearchViewAction()

    data object OnEnableRefreshButtonAndClearRecyclerViewAction : UserSearchViewAction()
}
