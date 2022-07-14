package com.example.myapplicationapi.MainFragment

import androidx.lifecycle.*
import com.example.data.models.SomethingDB
import com.example.data.repositories.SomethingRepository
import com.example.myapplicationapi.Data.Retrofit.Common
import com.example.myapplicationapi.Data.Retrofit.RetrofitServices
import com.example.myapplicationapi.DataModel.Items
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MyViewModel: ViewModel() {

    private val _listChanges = MutableLiveData<List<Items>>()
    val listChanges: LiveData<List<Items>> = _listChanges
    private var mService: RetrofitServices = Common.retrofitService

    fun observeAllSomething() = SomethingRepository.instance.getAllSomethingData()

    init {
        getAllMovieList()
    }

    fun searchDatabase(searchQuery: String): LiveData<List<SomethingDB>> =
         SomethingRepository.instance.searchDataBase(searchQuery).asLiveData()


    private fun getAllMovieList() {
        mService.getMovieList().enqueue(object : Callback<List<Items>> {
            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                viewModelScope.launch {
                    SomethingRepository.instance.deleteAllTable() //если приходит ответ то очищаем таблицу, и заполняем по новой
                    val listSomethingData: List<SomethingDB> = response.body()!!
                        .map{ SomethingDB(uuid = UUID.randomUUID().toString() ,
                            it.name!!, it.description!!, it.imageAvatar!!) }
                    SomethingRepository.instance.addAllData(listSomethingData)
                }
            }
        })
    }


    fun observeSortByName() = SomethingRepository.instance.sortByName()

}