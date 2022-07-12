package com.example.myapplicationapi.Data.Retrofit

import com.example.myapplicationapi.Items
import retrofit2.Call
import retrofit2.http.*


interface RetrofitServices {
    @GET("beers")
    fun getMovieList(): Call<MutableList<Items>>
}