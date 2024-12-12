package com.example.userapp.presentation.activity.domain

import com.example.userapp.core.base.viewmodel.AppViewState


data class MainViewState(
    val mainActionState: MainActionState = MainActionState.NULL,
) : AppViewState
