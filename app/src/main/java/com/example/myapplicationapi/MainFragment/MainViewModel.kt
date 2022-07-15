package com.example.myapplicationapi.MainFragment

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.data.models.DataBaseModel
import com.example.data.repositories.SomethingRepository
import com.example.myapplicationapi.Data.Retrofit.Common
import com.example.myapplicationapi.Data.Retrofit.RetrofitServices
import com.example.myapplicationapi.DataModel.Items
import com.example.myapplicationapi.DataModel.intResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MyViewModel: ViewModel() {

    private var mService: RetrofitServices = Common.retrofitService

    init {
        getAllItemList(1)
    }

    fun observeAllSomething(): LiveData<List<DataBaseModel>> =
        SomethingRepository.instance.getAllSomethingData().asLiveData()

    fun searchDatabase(searchQuery: String): LiveData<List<DataBaseModel>> =
         SomethingRepository.instance.searchDataBase(searchQuery).asLiveData()

    fun getAllItemList(page: Int) {
        mService.getItemList(page).enqueue(object : Callback<List<Items>> {
            override fun onFailure(call: Call<List<Items>>, t: Throwable) {
                Log.e("Response", "Проблемы с интернет соеденением")
            }
            override fun onResponse(call: Call<List<Items>>, response: Response<List<Items>>) {
                viewModelScope.launch {
                    val listData: List<DataBaseModel> = response.body()!!
                        .map{ DataBaseModel(it.id,
                            it.name!!, it.description!!, it.imageAvatar!!) }
                    SomethingRepository.instance.addAllData(listData)
                }
                isLoading = false
                println(response.body())
            }
        })
    }

    fun observeSortByName(): LiveData<List<DataBaseModel>> = SomethingRepository.instance.sortByName().asLiveData()

}