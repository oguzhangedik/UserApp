package com.example.userapp.ui.activity.domain

import com.example.userapp.core.platform.viewmodel.AppViewState
import com.example.userapp.model.UiState


data class MainViewState(
    val mainActionState: MainActionState = MainActionState.NULL,
    override val uiState: UiState = UiState.SUCCESS
) : AppViewState
