package com.example.myapplicationapi.Data.Retrofit

object Common {
    private val BASE_URL = "https://api.punkapi.com/v2/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}