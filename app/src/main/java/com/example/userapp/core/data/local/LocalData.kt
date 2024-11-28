package com.example.userapp.core.data.local

import com.example.userapp.core.data.Result
import com.example.userapp.core.data.dto.login.LoginRequest
import com.example.userapp.core.data.dto.login.LoginResponse

interface LocalData {
    fun doLogin(loginRequest: LoginRequest): Result<LoginResponse>
    fun doTest(): Result<List<String>>
}
