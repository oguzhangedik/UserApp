package com.example.userapp.core.platform

sealed class BaseViewEvent {
    data class ShowCustomError(val message: String) : BaseViewEvent()
    data class ShowInfoMessage(val message: String) : BaseViewEvent()
}
