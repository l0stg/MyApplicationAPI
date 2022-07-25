package com.example.myapplicationapi.Data.Retrofit
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.data.models.DataBaseModel
import com.example.data.repositories.SomethingRepository

class Repository(
    private val myDataBase: SomethingRepository
){

    suspend fun getItem(page: Int) {
        val itemsList: List<DataBaseModel>?
        itemsList = RetrofitClient.api.getItemList(page).body() ?: listOf()
        myDataBase.addAllData(itemsList)
    }

    fun searchDataBase(searchQuery: String): LiveData<List<DataBaseModel>> =
        myDataBase.searchDataBase(searchQuery).asLiveData()

    fun updateData(): LiveData<List<DataBaseModel>> = myDataBase.getAllSomethingData().asLiveData()

    fun sortByName(askSort: Int): LiveData<List<DataBaseModel>> =
        myDataBase.sortByName(askSort).asLiveData()

}