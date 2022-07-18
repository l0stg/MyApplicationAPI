package com.example.myapplicationapi.Data.Retrofit

import com.example.data.models.DataBaseModel
import retrofit2.Call
import retrofit2.http.*


interface RetrofitServices {
    @GET("beers")

    fun getItemList(
        @Query("page") page: Int
    ): Call<List<DataBaseModel>>
}