package com.example.myapplicationapi.Data.Retrofit
import com.example.data.models.DataBaseModel
import com.example.data.repositories.SomethingRepository

class Repository{
    suspend fun getItem(page: Int) {
        val itemsList: List<DataBaseModel>?
        itemsList = RetrofitClient.api.getItemList(page).body() ?: listOf()
        SomethingRepository().addAllData(itemsList)
    }
}