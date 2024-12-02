package com.example.userapp.core.base.viewmodel

import com.example.userapp.BuildConfig


abstract class AppViewModel <ViewState : AppViewState, out ViewAction : AppViewAction>(initialState: ViewState) :
    BaseViewModel<AppViewState, AppViewAction>(initialState) {

    init {
        if (BuildConfig.DEBUG) {
            stateTimeTravelDebugger = StateTimeTravelDebugger(this::class.java.simpleName)
        }
    }

}
