package com.example.signup

data class ResponseLoginData(
    val data: Data,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val email: String,
        val password: String
    )
}