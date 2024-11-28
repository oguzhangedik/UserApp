package com.example.userapp.ui.activity.presenter

import androidx.lifecycle.viewModelScope
import com.example.userapp.core.platform.viewmodel.AppViewAction
import com.example.userapp.core.platform.viewmodel.AppViewModel
import com.example.userapp.core.platform.viewmodel.AppViewState
import com.example.userapp.ui.activity.domain.MainViewAction
import com.example.userapp.ui.activity.domain.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
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
