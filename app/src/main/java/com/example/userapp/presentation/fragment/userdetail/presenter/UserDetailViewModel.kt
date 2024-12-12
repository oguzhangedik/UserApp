package com.example.userapp.presentation.fragment.userdetail.presenter

import androidx.lifecycle.viewModelScope
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.data.dto.userdetail.GithubUserDetailRequest
import com.example.userapp.domain.model.UserDetailHeaderItem
import com.example.userapp.data.local.LocalData
import com.example.userapp.domain.usecase.GithubUserDetailUseCase
import com.example.userapp.core.extensions.safeLet
import com.example.userapp.core.extensions.toGithubUserDetail
import com.example.userapp.core.netwok.data.Status
import com.example.userapp.core.base.viewmodel.AppViewAction
import com.example.userapp.core.base.viewmodel.AppViewModel
import com.example.userapp.core.base.viewmodel.AppViewState
import com.example.userapp.presentation.fragment.userdetail.domain.UserDetailActionState
import com.example.userapp.presentation.fragment.userdetail.domain.UserDetailMapper
import com.example.userapp.presentation.fragment.userdetail.domain.UserDetailViewAction
import com.example.userapp.presentation.fragment.userdetail.domain.UserDetailViewState
import com.example.userapp.core.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val githubUserDetailUseCase: GithubUserDetailUseCase,
    private val userDetailMapper : UserDetailMapper,
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
                       githubUserDetail = dbUserDetail,
                       userDetails = userDetailMapper.map(githubUser, dbUserDetail)
                   ))
               } ?: run {
                   val userDetailResponse = githubUserDetailUseCase.githubUserDetail(
                       request = GithubUserDetailRequest(login)
                   ).first()

                   if (userDetailResponse.status == Status.SUCCESS) {
                       userDetailResponse.data?.toGithubUserDetail()?.let { githubUserDetail ->

                           val dbIdOfGithubUserDetail
                           = localRepository.insertGithubUserDetail(githubUserDetail)

                           githubUserDetail.dbId = dbIdOfGithubUserDetail

                           sendAction(viewAction = UserDetailViewAction.OnGithubUserDetail(
                               githubUser = githubUser,
                               githubUserDetail = githubUserDetail,
                               userDetails = userDetailMapper.map(githubUser, githubUserDetail)
                           ))
                       } ?: run {
                           showCustomError(UserDetail.USER_DETAIL_EMPTY)
                       }
                   } else {
                       userDetailResponse.error?.message?.let { errorMessage ->
                           showCustomError(errorMessage)
                       } ?: run {
                           showCustomError(SOMETHING_WENT_WRONG)
                       }
                       setLoading(false)
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
                    githubUser = action.githubUser,
                    githubUserDetail = action.githubUserDetail,
                    userDetails = action.userDetails,
                    userDetailActionState = UserDetailActionState.GET_GITHUB_USER_DETAIL
                )
            }
            is UserDetailViewAction.OnFavoriteStateChanged -> {
                userDetailViewState.copy(
                    githubUser = action.githubUser,
                    userDetailActionState = if(action.githubUser?.isFavorite == true)
                        UserDetailActionState.FAVORITE_STATE_CHANGED_TO_FAVORITE
                    else UserDetailActionState.FAVORITE_STATE_CHANGED_TO_UNFAVORITE
                )
            }
        }
    }

    fun updateGithubUserFavoriteState(){
        safeLet(userDetailViewState.githubUser, userDetailViewState.userDetails)
        { githubUser, userDetails ->
            viewModelScope.launch(coroutine) {
                githubUser.isFavorite = githubUser.isFavorite != true
                localRepository.updateGithubUser(githubUser)
                userDetails.filterIsInstance<UserDetailHeaderItem>()
                    .firstOrNull()?.isFavorite = githubUser.isFavorite ?: false
                sendAction(viewAction = UserDetailViewAction
                    .OnFavoriteStateChanged(githubUser = githubUser))
            }
        }
    }

}
