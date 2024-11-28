package com.example.userapp.core.platform.viewmodel

import com.example.userapp.BuildConfig
import com.example.userapp.core.extensions.safeLet
import com.example.userapp.core.datastore.EncryptedAppDataStore
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

abstract class AppViewModel <ViewState : AppViewState, out ViewAction : AppViewAction>(initialState: ViewState) :
    BaseViewModel<AppViewState, AppViewAction>(initialState) {

        @Inject
        lateinit var dataStore: EncryptedAppDataStore

    init {
        if (BuildConfig.DEBUG) {
            stateTimeTravelDebugger = StateTimeTravelDebugger(this::class.java.simpleName)
        }
    }
    
}
