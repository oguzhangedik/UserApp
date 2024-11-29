package com.example.userapp.ui.userdetail.domain


import com.example.userapp.core.platform.viewmodel.AppViewState
import com.example.userapp.model.UiState


data class UserDetailViewState(
    val userDetailActionState: UserDetailActionState = UserDetailActionState.NULL,


    override val uiState: UiState = UiState.SUCCESS
) : AppViewState
