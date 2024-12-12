package com.example.userapp.presentation.activity.presenter

import com.example.userapp.core.base.viewmodel.AppViewAction
import com.example.userapp.core.base.viewmodel.AppViewModel
import com.example.userapp.core.base.viewmodel.AppViewState
import com.example.userapp.presentation.activity.domain.MainViewAction
import com.example.userapp.presentation.activity.domain.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor(
    private val coroutine: CoroutineContext
) : AppViewModel<MainViewState, MainViewAction>(
    MainViewState()
) {

    val mainViewState: MainViewState
        get() = state as MainViewState



    override fun onReduceState(viewAction: AppViewAction): AppViewState {
        return mainViewState
    }
}
