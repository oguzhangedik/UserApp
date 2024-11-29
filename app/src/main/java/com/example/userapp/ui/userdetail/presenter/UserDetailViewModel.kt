package com.example.userapp.ui.userdetail.presenter

import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.usecase.githubuser.GithubUserUseCase
import com.example.userapp.core.platform.viewmodel.AppViewAction
import com.example.userapp.core.platform.viewmodel.AppViewModel
import com.example.userapp.core.platform.viewmodel.AppViewState
import com.example.userapp.ui.userdetail.domain.UserDetailViewAction
import com.example.userapp.ui.userdetail.domain.UserDetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val githubUserUseCase: GithubUserUseCase,
    private val localRepository: LocalData,
    private val coroutine: CoroutineContext
) : AppViewModel<UserDetailViewState, UserDetailViewAction>(UserDetailViewState()) {

    val userDetailViewState: UserDetailViewState
        get() = state as UserDetailViewState



    override fun onReduceState(viewAction: AppViewAction): AppViewState {
        return userDetailViewState
    }

}
