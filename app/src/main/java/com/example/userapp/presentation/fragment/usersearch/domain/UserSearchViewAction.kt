package com.example.userapp.presentation.fragment.usersearch.domain

import com.example.userapp.domain.model.BaseListItemOfGithubUser
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.core.base.viewmodel.AppViewAction


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
