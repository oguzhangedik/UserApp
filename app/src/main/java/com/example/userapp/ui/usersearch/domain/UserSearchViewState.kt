package com.example.userapp.ui.usersearch.domain

import com.example.userapp.core.platform.viewmodel.AppViewState
import com.example.userapp.model.UiState


data class UserSearchViewState(
    val userSearchActionState: UserSearchActionState = UserSearchActionState.NULL,
    /*    val otpFormDataList: OtpFormDataList? = null,
        val otpTimer : OtpTimer? = null,

        val registerFormDataList: RegisterFormDataList? = null,
        var registerRequestToken : String? = null,

        val forgetPassFormDataList : ForgetPassFormDataList? = null,
        val recoverPassRequestToken : String? = null,

        val loginFormDataList: LoginFormDataList? = null,
        val loginRequestToken : String? = null,
        val loginValidationToken : String? = null,*/

    override val uiState: UiState = UiState.SUCCESS
) : AppViewState
