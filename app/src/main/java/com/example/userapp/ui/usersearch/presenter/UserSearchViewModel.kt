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

            val newSearchRequest = GithubUserSearchRequest()

            localRepository.getByUserSearchRequestParams(newSearchRequest)
                .firstOrNull()?.let { dbUserSearchRequest ->
                // direk db den getir diycez ama simdi test ediyoruz
               val githubUsersFromDb =
                   localRepository.getGithubUsersBySearchRequestDbId(dbUserSearchRequest.dbId)
               githubUsersFromDb
               showInfoMessage("direk db den")
               setLoading(false)
            } ?: run {
               val searchUserResponse = githubUserUseCase.searchGithubUsers(
                   request = newSearchRequest
               ).first()
               if (searchUserResponse.status == Status.SUCCESS) {
                   searchUserResponse.data?.items?.let { githubUsers ->
                       val newDbUserSearchRequestDbId
                       = localRepository.insertGithubUserSearchRequest(newSearchRequest)
                       githubUsers.forEach { githubUser
                           ->  githubUser.searchRequestDbId = newDbUserSearchRequestDbId
                       }
                       val githubUsersDbIds = localRepository.insertGithubUsers(githubUsers)

                       for (i in githubUsers.indices) {
                           githubUsers[i].dbId = githubUsersDbIds[i]
                       }

                       showInfoMessage("success")
                   }
                   showInfoMessage("successssss")
                   setLoading(false)
               } else {
                   showCustomError("hata")
                   setLoading(false)
               }
            }
        }
    }

    val userSearchViewState: UserSearchViewState
        get() = state as UserSearchViewState

    override fun onReduceState(viewAction: AppViewAction): AppViewState {
        return userSearchViewState
    }
}
