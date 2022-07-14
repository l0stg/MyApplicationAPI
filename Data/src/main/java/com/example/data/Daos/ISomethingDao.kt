package com.example.data.Daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.models.SomethingDB
import kotlinx.coroutines.flow.Flow


@Dao
interface ISomethingDao {

    @Query("SELECT * FROM something_table")
    fun getAllSomethingData(): LiveData<List<SomethingDB>>

    @Query("SELECT * from something_table ORDER BY sm_title DESC")
    fun getSortByName(): LiveData<List<SomethingDB>>

    @Insert
    suspend fun addAllData(newData: List<SomethingDB>)

    @Query("DELETE FROM something_table")
    suspend fun nukeTable()

    @Query("SELECT * FROM something_table WHERE sm_title LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<SomethingDB>>

}