package com.example.signup

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SoptService{

    //회원가입
    @Headers("Content-Type:application/json")
    @POST("/users/signup")
    fun postSignup(
        @Body body:RequestSignupData
    ):Call<ResponseSignupData>
    //로그인
    @Headers("Content-Type:application/json")
    @POST("/users/signin")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>
}