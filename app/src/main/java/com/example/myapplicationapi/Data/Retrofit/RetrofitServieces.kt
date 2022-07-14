package com.example.myapplicationapi.Data.Retrofit

import com.example.myapplicationapi.DataModel.Items
import retrofit2.Call
import retrofit2.http.*


interface RetrofitServices {
    @GET("beers")
    fun getMovieList(): Call<List<Items>>
}