package com.example.userapp.ui.usersearch.presenter

import androidx.lifecycle.viewModelScope
import com.example.userapp.core.data.dto.user.GithubUserSearchRequest
import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.usecase.githubuser.GithubUserUseCase
import com.example.userapp.core.netwok.data.Status
import com.example.userapp.core.platform.viewmodel.AppViewAction
import com.example.userapp.core.platform.viewmodel.AppViewModel
import com.example.userapp.core.platform.viewmodel.AppViewState
import com.example.userapp.ui.usersearch.domain.UserSearchViewAction
import com.example.userapp.ui.usersearch.domain.UserSearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val githubUserUseCase: GithubUserUseCase,
    private val localRepository: LocalData,
    private val coroutine: CoroutineContext
) : AppViewModel<UserSearchViewState, UserSearchViewAction>(UserSearchViewState()) {

    fun searchGithubUsers() {
        viewModelScope.launch(coroutine) {
            setLoading(true)
            val searchUserResponse = githubUserUseCase.searchGithubUsers(
                request = GithubUserSearchRequest()
            ).first()
            setLoading(false)
            if (searchUserResponse.status == Status.SUCCESS) {
                showInfoMessage("success")
            } else {
                showCustomError("hata")
            }
        }
    }

    val userSearchViewState: UserSearchViewState
        get() = state as UserSearchViewState

    override fun onReduceState(viewAction: AppViewAction): AppViewState {
        return userSearchViewState
    }
}
