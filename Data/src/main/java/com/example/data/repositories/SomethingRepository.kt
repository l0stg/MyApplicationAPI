package com.example.data.repositories


import com.example.data.Provider
import com.example.data.models.DataBaseModel
import kotlinx.coroutines.flow.Flow


class SomethingRepository {
    private val myDao = Provider.instance.somethingDao()

    companion object{
        val instance = SomethingRepository()
    }

    fun getAllSomethingData(): Flow<List<DataBaseModel>> {
        return myDao.getAllSomethingData()
    }

    fun sortByName(): Flow<List<DataBaseModel>> = myDao.getSortByName()

    fun searchDataBase(searchQuery: String): Flow<List<DataBaseModel>> {
        return myDao.searchDatabase(searchQuery)
    }

    suspend fun addAllData(newList: List<DataBaseModel>) {
        myDao.addAllData(newList)
    }

    suspend fun deleteAllTable() = myDao.nukeTable()

    suspend fun updateData(model: DataBaseModel) = myDao.updateData(model)




}