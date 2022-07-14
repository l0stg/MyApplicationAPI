package com.example.data.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.data.models.DataBaseModel
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    @Query("SELECT * FROM my_table")
    fun getAllSomethingData(): Flow<List<DataBaseModel>>

    @Query("SELECT * from my_table ORDER BY title DESC")
    fun getSortByName(): Flow<List<DataBaseModel>>

    @Insert
    suspend fun addAllData(newData: List<DataBaseModel>)

    @Query("DELETE FROM my_table")
    suspend fun nukeTable()

    @Query("SELECT * FROM my_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<DataBaseModel>>

/*

    @Query("SELECT * FROM my_table WHERE title NOT LIKE :name OR subtitle NOT LIKE :description OR image NOT LIKE :imageAV")
    fun compareData(name: String, description: String, imageAV: String): DataBaseModel

    @Update
    suspend fun updateData(model: DataBaseModel)
*/


}