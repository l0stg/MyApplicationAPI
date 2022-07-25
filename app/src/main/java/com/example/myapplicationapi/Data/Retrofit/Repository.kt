package com.example.myapplicationapi.Data.Retrofit
import com.example.data.models.DataBaseModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    suspend fun getItem(page: Int): List<DataBaseModel>? = RetrofitClient.api.getItemList(page).body()
}