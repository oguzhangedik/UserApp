package com.example.userapp.core.data.dto.login

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginResponse(
    @PrimaryKey val Id: String = "",
    val firstName: String,
    val lastName: String,
    val email: String,
    val token: String

)


