package com.example.userapp.core.data.dto

data class SuccessResponse(
    val datas: Data,
    val message: String,
    val successCode: Int
) {
    class Data
}
