package com.example.signup

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceImpl {

    var retrofit = Retrofit.Builder()
        .baseUrl("http://15.164.83.210:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: SoptService = retrofit.create(SoptService::class.java)
}