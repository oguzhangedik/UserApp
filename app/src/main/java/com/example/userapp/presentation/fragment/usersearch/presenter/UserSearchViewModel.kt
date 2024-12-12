package com.example.userapp.presentation.fragment.usersearch.presenter

import androidx.lifecycle.viewModelScope
import com.example.userapp.domain.model.BaseListItemOfGithubUser
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.data.dto.usersearch.GithubUserSearchRequest
import com.example.userapp.domain.model.NoItemOfGithubUser
import com.example.userapp.domain.model.ProgressItemOfGithubUser
import com.example.userapp.data.local.LocalData
import com.example.userapp.domain.usecase.GithubUserSearchUseCase
import com.example.userapp.core.extensions.safeLet
import com.example.userapp.core.extensions.toGithubUsers
import com.example.userapp.core.netwok.data.Status
import com.example.userapp.core.base.viewmodel.AppViewAction
import com.example.userapp.core.base.viewmodel.AppViewModel
import com.example.userapp.core.base.viewmodel.AppViewState
import com.example.userapp.presentation.fragment.usersearch.domain.UserSearchActionState
import com.example.userapp.presentation.fragment.usersearch.domain.UserSearchViewAction
import com.example.userapp.presentation.fragment.usersearch.domain.UserSearchViewState
import com.example.userapp.core.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.delay

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val githubUserUseCase: GithubUserSearchUseCase,
    private val localRepository: LocalData,
    private val coroutine: CoroutineContext
) : AppViewModel<UserSearchViewState, UserSearchViewAction>(UserSearchViewState()) {

    val userSearchViewState: UserSearchViewState
        get() = state as UserSearchViewState

    private var searchTextDebounceJob: Job? = null
    private var loadMoreDebounceJob: Job? = null

    fun searchGithubUsers() {
        viewModelScope.launch(coroutine) {
            if(isFirstRequest()) setLoading(true)
            val newSearchRequest = generateNextPageUserSearchRequest(userSearchViewState.searchText)
            localRepository.getByUserSearchRequestParams(newSearchRequest)
                .firstOrNull()?.let { dbUserSearchRequest ->
                    val githubUsersFromDb =
                        localRepository.getGithubUsersBySearchRequestDbId(dbUserSearchRequest.dbId)
                    mapGithubUserListAndSendAction(newSearchRequest, ArrayList(githubUsersFromDb))
                    setLoading(false)
                } ?: run {
                val searchUserResponse = githubUserUseCase.searchGithubUsers(
                    request = newSearchRequest
                ).first()
                if (searchUserResponse.status == Status.SUCCESS) {
                    searchUserResponse.data?.items?.toGithubUsers()?.let { githubUsers ->
                        val newDbUserSearchRequestDbId =
                            localRepository.insertGithubUserSearchRequest(newSearchRequest)
                        githubUsers.forEach { githubUser ->
                            githubUser.searchRequestDbId = newDbUserSearchRequestDbId
                        }
                        val githubUsersDbIds = localRepository.insertGithubUsers(githubUsers)
                        for (indexOfGithubUser in githubUsers.indices) {
                            githubUsers[indexOfGithubUser].dbId =
                                githubUsersDbIds[indexOfGithubUser]
                        }
                        mapGithubUserListAndSendAction(newSearchRequest, ArrayList(githubUsers))
                    }
                    setLoading(false)
                } else {
                    searchUserResponse.error?.message?.let { errorMessage ->
                        showCustomError(errorMessage)
                        if (isFirstRequest()) {
                            sendAction(viewAction = UserSearchViewAction.OnDisableLoadMoreAction)
                            sendAction(viewAction = UserSearchViewAction.OnEnableRefreshButtonAndClearRecyclerViewAction)
                        } else {
                            sendAction(viewAction = UserSearchViewAction.OnEnableLoadMoreAction)
                            sendAction(viewAction = UserSearchViewAction.OnClearAction)
                        }

                    } ?: run {
                        showCustomError(SOMETHING_WENT_WRONG)
                    }
                    setLoading(false)
                }
            }
        }
    }

    override fun onReduceState(viewAction: AppViewAction): AppViewState {
        return when (val action = viewAction as UserSearchViewAction) {
            is UserSearchViewAction.OnClearAction -> {
                userSearchViewState.copy(
                    userSearchActionState = UserSearchActionState.NULL
                )
            }
            is UserSearchViewAction.OnEnableLoadMoreAction -> {
                userSearchViewState.copy(
                    userSearchActionState = UserSearchActionState.ENABLE_LOAD_MORE_ACTION
                )
            }
            is UserSearchViewAction.OnDisableLoadMoreAction -> {
                userSearchViewState.copy(
                    userSearchActionState = UserSearchActionState.DISABLE_LOAD_MORE_ACTION
                )
            }
            is UserSearchViewAction.OnEnableRefreshButtonAndClearRecyclerViewAction -> {
                userSearchViewState.copy(
                    userSearchActionState = UserSearchActionState.ENABLE_REFRESH_BUTTON_AND_CLEAR_RECYCLER_VIEW
                )
            }
            is UserSearchViewAction.OnGithubUsers -> {
                userSearchViewState.copy(
                    githubUserSearchRequest = action.githubUserSearchRequest,
                    githubUsers = action.githubUsers,
                    userSearchActionState = UserSearchActionState.GET_GITHUB_USERS
                )
            }

            is UserSearchViewAction.OnLoadMoreGithubUsers -> {
                userSearchViewState.copy(
                    githubUserSearchRequest = action.githubUserSearchRequest,
                    githubUsers = action.githubUsers,
                    userSearchActionState = UserSearchActionState.LOAD_MORE_GITHUB_USERS
                )
            }

            is UserSearchViewAction.OnSearchNewGithubUsers -> {
                userSearchViewState.copy(
                    searchText = action.searchText,
                    githubUserSearchRequest = null,
                    githubUsers = null,
                    lastFavoriteUpdateGithubUser = null,
                    userSearchActionState = UserSearchActionState.SEARCH_NEW_GITHUB_USERS
                )
            }

            is UserSearchViewAction.OnGithubUserFavoriteStateUpdate -> {
                userSearchViewState.copy(
                    lastFavoriteUpdateGithubUser = action.githubUser,
                    userSearchActionState =
                    if(action.githubUser.isFavorite == true)
                        UserSearchActionState.UPDATE_GITHUB_USER_TO_FAVORITE_STATE
                    else UserSearchActionState.UPDATE_GITHUB_USER_TO_UNFAVORITE_STATE
                )
            }
        }
    }

    private fun generateNextPageUserSearchRequest(textInUserNameToSearch: String)
            : GithubUserSearchRequest {
        val currentGithubUserSearchRequest =
            userSearchViewState.githubUserSearchRequest ?: GithubUserSearchRequest()
        return currentGithubUserSearchRequest.copy(
            textInUserNameToSearch = textInUserNameToSearch,
            page = currentGithubUserSearchRequest.page + 1
        )
    }

    private fun mapGithubUserListAndSendAction(
        newRequest: GithubUserSearchRequest,
        newGithubUsers: ArrayList<GithubUser>
    ) {
        if (isFirstRequest()) {
            val githubUserListItems = ArrayList<BaseListItemOfGithubUser>(newGithubUsers)
            if (githubUserListItems.isEmpty()) {
                githubUserListItems.add(NoItemOfGithubUser())
                sendAction(viewAction = UserSearchViewAction.OnDisableLoadMoreAction)
            } else if (newGithubUsers.size == UserSearch.LIST_PAGE_ITEM_COUNT) {
                githubUserListItems.add(ProgressItemOfGithubUser())
                sendAction(viewAction = UserSearchViewAction.OnEnableLoadMoreAction)
            } else {
                sendAction(viewAction = UserSearchViewAction.OnDisableLoadMoreAction)
            }
            sendAction(
                viewAction = UserSearchViewAction.OnGithubUsers(
                    githubUserSearchRequest = newRequest,
                    githubUsers = githubUserListItems
                )
            )
        } else {
            safeLet(
                userSearchViewState.githubUserSearchRequest,
                userSearchViewState.githubUsers
            ) { currentRequest, currentGithubUsers ->
                val stateGithubUserList = currentGithubUsers.filterIsInstance<GithubUser>()
                val githubUserListItems = ArrayList<BaseListItemOfGithubUser>()
                githubUserListItems.addAll(stateGithubUserList)
                githubUserListItems.addAll(newGithubUsers)
                if (newGithubUsers.size == UserSearch.LIST_PAGE_ITEM_COUNT) {
                    githubUserListItems.add(ProgressItemOfGithubUser())
                    sendAction(viewAction = UserSearchViewAction.OnEnableLoadMoreAction)
                } else {
                    sendAction(viewAction = UserSearchViewAction.OnDisableLoadMoreAction)
                }
                sendAction(
                    viewAction = UserSearchViewAction.OnLoadMoreGithubUsers(
                        githubUserSearchRequest = newRequest,
                        githubUsers = githubUserListItems
                    )
                )
            }
        }
    }

    private fun isFirstRequest() = (userSearchViewState.githubUserSearchRequest == null
                && userSearchViewState.githubUsers == null)

    fun updateSearchText(newText: String) {
        searchTextDebounceJob?.cancel()
        searchTextDebounceJob = viewModelScope.launch(coroutine) {
            delay(UserSearch.UPDATE_SEARCH_TEXT_JOB_DELAY)
            newText.trim().let {
                if (it.isNotEmpty() && it.isNotBlank()) {
                    sendAction(viewAction = UserSearchViewAction.OnEnableLoadMoreAction)
                    sendAction(
                        viewAction = UserSearchViewAction.OnSearchNewGithubUsers(
                            searchText = it
                        )
                    )
                }
            }
        }
    }

    fun loadMoreData() {
        loadMoreDebounceJob?.cancel()
        loadMoreDebounceJob = viewModelScope.launch(coroutine) {
            delay(UserSearch.LOAD_MORE_JOB_DELAY)
            searchGithubUsers()
        }
    }

    fun updateGithubUserFavoriteState(githubUser: GithubUser?){
        githubUser?.let {
            viewModelScope.launch(coroutine) {
                githubUser.isFavorite = githubUser.isFavorite != true
                localRepository.updateGithubUser(it)
                sendAction(viewAction = UserSearchViewAction
                    .OnGithubUserFavoriteStateUpdate(githubUser = githubUser))
            }
        }
    }

    fun clearActionState() {
        sendAction(viewAction = UserSearchViewAction.OnClearAction)
    }
}
