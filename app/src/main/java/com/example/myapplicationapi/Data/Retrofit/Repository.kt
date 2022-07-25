package com.example.myapplicationapi.Data.Retrofit
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.data.models.DataBaseModel
import com.example.data.repositories.SomethingRepository
import kotlinx.coroutines.flow.Flow

class Repository(
    private val myDataBase: SomethingRepository
){

    suspend fun getItem(page: Int) {
        val itemsList: List<DataBaseModel>?
        itemsList = RetrofitClient.api.getItemList(page).body() ?: listOf()
        myDataBase.addAllData(itemsList)
    }

    fun searchDataBase(searchQuery: String): Flow<List<DataBaseModel>> =
        myDataBase.searchDataBase(searchQuery)

    fun getAllSomethingData(): Flow<List<DataBaseModel>> = myDataBase.getAllSomethingData()

    fun sortByName(askSort: Int): Flow<List<DataBaseModel>> =
        myDataBase.sortByName(askSort)

}