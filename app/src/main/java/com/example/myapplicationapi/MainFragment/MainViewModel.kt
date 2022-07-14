package com.example.myapplicationapi.MainFragment

import androidx.lifecycle.*
import com.example.data.models.DataBaseModel
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

    private var mService: RetrofitServices = Common.retrofitService

    init {
        getAllMovieList()
    }

    fun observeAllSomething(): LiveData<List<DataBaseModel>> =
        SomethingRepository.instance.getAllSomethingData().asLiveData()

    fun searchDatabase(searchQuery: String): LiveData<List<DataBaseModel>> =
         SomethingRepository.instance.searchDataBase(searchQuery).asLiveData()

    private fun getAllMovieList() {
        mService.getMovieList().enqueue(object : Callback<List<Items>> {
            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
            }
            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                viewModelScope.launch {
                    SomethingRepository.instance.deleteAllTable() //если приходит ответ то очищаем таблицу, и заполняем по новой
                    val listData: List<DataBaseModel> = response.body()!!
                        .map{ DataBaseModel(uuid = UUID.randomUUID().toString() ,
                            it.name!!, it.description!!, it.imageAvatar!!) }
                    SomethingRepository.instance.addAllData(listData)
                }
            }
        })
    }
    fun observeSortByName(): LiveData<List<DataBaseModel>> = SomethingRepository.instance.sortByName().asLiveData()

/*    fun updateData(model: DataBaseModel){
        val newModel: DataBaseModel
        newModel = DataBaseModel(model.uuid, model.name, model.description, model.imageAvatar)

    }
    fun compareDataNewResponse(newData: List<DataBaseModel>){
        newData.forEach {

            updateData(it)
        }

    }*/
}