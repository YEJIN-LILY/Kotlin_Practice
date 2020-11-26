package com.example.signup

data class ResponseSignupData(
    val data: Data,
    val status: Int,
    val success: Boolean
) {
    data class Data(
        val email: String,
        val password: String
    )
}