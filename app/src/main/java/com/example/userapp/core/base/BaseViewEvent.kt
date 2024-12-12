package com.example.userapp.core.base

sealed class BaseViewEvent {
    data class ShowCustomError(val message: String) : BaseViewEvent()
    data class ShowInfoMessage(val message: String) : BaseViewEvent()
}
