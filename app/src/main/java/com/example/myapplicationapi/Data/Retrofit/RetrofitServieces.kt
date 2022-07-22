package com.example.myapplicationapi.Data.Retrofit

import com.example.data.models.DataBaseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface RetrofitServices {
    @GET("beers")

    suspend fun getItemList(
        @Query("page") page: Int
    ): Response<List<DataBaseModel>>
}