package com.example.userapp.presentation.fragment.userdetail.domain

import com.example.userapp.domain.model.BaseListItemOfGithubUserDetail
import com.example.userapp.domain.model.GithubUser
import com.example.userapp.domain.model.GithubUserDetail
import com.example.userapp.core.base.viewmodel.AppViewAction


sealed class UserDetailViewAction : AppViewAction {
    data class OnGithubUserDetail(
        val githubUser : GithubUser? = null,
        val githubUserDetail : GithubUserDetail? = null,
        val userDetails: ArrayList<BaseListItemOfGithubUserDetail>? = null
    ) : UserDetailViewAction()

    data class OnFavoriteStateChanged(
        val githubUser : GithubUser? = null
    ) : UserDetailViewAction()
}
