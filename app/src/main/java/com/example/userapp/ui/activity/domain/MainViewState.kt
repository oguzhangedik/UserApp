package com.example.userapp.ui.activity.domain

import com.example.userapp.core.platform.viewmodel.AppViewState


data class MainViewState(
    val mainActionState: MainActionState = MainActionState.NULL,
) : AppViewState
