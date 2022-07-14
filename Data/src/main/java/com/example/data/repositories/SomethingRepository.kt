package com.example.data.repositories

import com.example.data.DBProvider
import com.example.data.models.SomethingDB

class SomethingRepository {
    private val somethingDao = DBProvider.instance.somethingDao()

    companion object{
        val instance = SomethingRepository()
    }

    fun getAllSomethingData() = somethingDao.getAllSomethingData()

    fun sortByName() = somethingDao.getSortByName()

    fun searchDataBase(searchQuery: String): kotlinx.coroutines.flow.Flow<List<SomethingDB>> {
        return somethingDao.searchDatabase(searchQuery)
    }

    suspend fun addAllData(newList: List<SomethingDB>) {
        somethingDao.addAllData(newList)
    }

    suspend fun deleteAllTable() = somethingDao.nukeTable()


}