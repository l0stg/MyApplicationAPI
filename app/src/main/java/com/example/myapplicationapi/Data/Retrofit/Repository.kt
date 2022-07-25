package com.example.myapplicationapi.Data.Retrofit

import com.example.data.Daos.Dao
import com.example.data.models.DataBaseModel
import kotlinx.coroutines.flow.Flow

class Repository(
    private val api: RetrofitServices,
    private val myDao: Dao
){
    suspend fun getItem(page: Int) {
        val itemsList: List<DataBaseModel>?
        itemsList = api.getItemList(page).body() ?: listOf()
        myDao.addAllData(itemsList)
    }

    fun searchDataBase(searchQuery: String): Flow<List<DataBaseModel>> =
        myDao.searchDataBase(searchQuery)

    fun getAllSomethingData(): Flow<List<DataBaseModel>> = myDao.getAllSomethingData()

    fun sortByName(askSort: Int): Flow<List<DataBaseModel>> =
        myDao.sortByName(askSort)

}