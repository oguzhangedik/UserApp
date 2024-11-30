package com.example.userapp.ui.userdetail.presenter

import androidx.lifecycle.viewModelScope
import com.example.userapp.core.data.dto.user.GithubUser
import com.example.userapp.core.data.dto.user.GithubUserDetailRequest
import com.example.userapp.core.data.local.LocalData
import com.example.userapp.core.data.usecase.githubuser.GithubUserDetailUseCase
import com.example.userapp.core.extensions.safeLet
import com.example.userapp.core.netwok.data.Status
import com.example.userapp.core.platform.viewmodel.AppViewAction
import com.example.userapp.core.platform.viewmodel.AppViewModel
import com.example.userapp.core.platform.viewmodel.AppViewState
import com.example.userapp.model.UiState
import com.example.userapp.ui.userdetail.domain.UserDetailActionState
import com.example.userapp.ui.userdetail.domain.UserDetailViewAction
import com.example.userapp.ui.userdetail.domain.UserDetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val githubUserDetailUseCase: GithubUserDetailUseCase,
    private val localRepository: LocalData,
    private val coroutine: CoroutineContext
) : AppViewModel<UserDetailViewState, UserDetailViewAction>(UserDetailViewState()) {

    val userDetailViewState: UserDetailViewState
        get() = state as UserDetailViewState

    fun githubUserDetail(githubUser: GithubUser?) {
        safeLet(githubUser, githubUser?.login) { githubUser, login ->
            viewModelScope.launch(coroutine) {
                setLoading(true)
               localRepository.getGithubUserDetailByGithubUserId(githubUser)?.let { dbUserDetail ->
                   sendAction(viewAction = UserDetailViewAction.OnGithubUserDetail(
                       githubUser = githubUser,
                       githubUserDetail = dbUserDetail
                   ))
               } ?: run {
                   val userDetailResponse = githubUserDetailUseCase.githubUserDetail(
                       request = GithubUserDetailRequest(login)
                   ).first()

                   if (userDetailResponse.status == Status.SUCCESS) {
                       userDetailResponse.data?.let { githubUserDetail ->

                           val dbIdOfGithubUserDetail
                           = localRepository.insertGithubUserDetail(githubUserDetail)

                           githubUserDetail.dbId = dbIdOfGithubUserDetail

                           sendAction(viewAction = UserDetailViewAction.OnGithubUserDetail(
                               githubUser = githubUser,
                               githubUserDetail = githubUserDetail
                           ))
                           showCustomError("success ${githubUserDetail.url}")
                       } ?: run {
                           showCustomError("hata user detail is null")
                       }
                   } else {
                       showCustomError("hata")
                   }
               }

                setLoading(false)
            }
        }
    }


    override fun onReduceState(viewAction: AppViewAction): AppViewState {
        return when (val action = viewAction as UserDetailViewAction) {
            is UserDetailViewAction.OnGithubUserDetail -> {
                userDetailViewState.copy(
                    uiState = UiState.SUCCESS,
                    githubUser = action.githubUser,
                    githubUserDetail = action.githubUserDetail,
                    userDetailActionState = UserDetailActionState.GET_GITHUB_USER_DETAIL
                )
            }
        }
    }

}
