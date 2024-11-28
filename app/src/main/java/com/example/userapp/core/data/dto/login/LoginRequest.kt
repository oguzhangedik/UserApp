package com.example.userapp.core.data.dto.login

data class LoginRequest(var userName: String, var userPassword: String)

fun LoginRequest.isExist(isEqualsUser: LoginRequest) = this.userName == isEqualsUser.userName && this.userPassword == isEqualsUser.userPassword
