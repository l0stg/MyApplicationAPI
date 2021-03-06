package com.example.data.Daos

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.data.models.DataBaseModel
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    @Query("SELECT * FROM my_table")
    fun getAllSomethingData(): Flow<List<DataBaseModel>>

    @Query("SELECT * FROM my_table ORDER BY " +
            "CASE WHEN :isAsc = 1  THEN title END ASC, " +
            "CASE WHEN :isAsc = 0 THEN title END DESC ")
    fun getSortByName(isAsc: Int): Flow<List<DataBaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllData(newData: List<DataBaseModel>)

    @Query("SELECT * FROM my_table WHERE title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<DataBaseModel>>
}