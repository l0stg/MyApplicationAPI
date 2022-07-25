package com.example.data.repositories


import com.example.data.Provider
import com.example.data.models.DataBaseModel
import kotlinx.coroutines.flow.Flow


class SomethingRepository {

    private val myDao = Provider.instance?.somethingDao()

    fun getAllSomethingData(): Flow<List<DataBaseModel>> {
        return myDao!!.getAllSomethingData()
    }

    fun sortByName(ask: Int): Flow<List<DataBaseModel>> = myDao!!.getSortByName(ask)

    fun searchDataBase(searchQuery: String): Flow<List<DataBaseModel>> {
        return myDao!!.searchDatabase(searchQuery)
    }

    suspend fun addAllData(newList: List<DataBaseModel>) {
        myDao!!.addAllData(newList)
    }

}