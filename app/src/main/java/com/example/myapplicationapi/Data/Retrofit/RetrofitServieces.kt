package com.example.myapplicationapi.Data.Retrofit

import com.example.myapplicationapi.DataModel.Items
import retrofit2.Call
import retrofit2.http.*


interface RetrofitServices {
    @GET("beers")

    fun getItemList(
        @Query("page") page: Int
    ): Call<List<Items>>
}