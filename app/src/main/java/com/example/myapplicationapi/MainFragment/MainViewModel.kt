package com.example.myapplicationapi.MainFragment

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    fun addStaticSomethingModel() {
        viewModelScope.launch {
            val model = SomethingDB(
                name = "name 1",
                description = "desc 1",
                imageAvatar = "https://images.punkapi.com/v2/2.png"
            )
            SomethingRepository.instance.addSomething(model)
        }
    }

    fun deleteSomethingModel(model: SomethingDB) {
        viewModelScope.launch {
            SomethingRepository.instance.deleteSomething(model)
        }
    }

    fun updateSomethingModel(model: SomethingDB){
        viewModelScope.launch {
            model.name = "update"
            SomethingRepository.instance.updateSomething(model)
        }
    }

    /*private fun init(){
       //getAllMovieList()
    }
    init {
        init()
        }
*/

    /*private fun getAllMovieList() {
        mService.getMovieList().enqueue(object : Callback<MutableList<Items>> {
            override fun onFailure(call: Call<MutableList<Items>>, t: Throwable) {
            }
            override fun onResponse(call: Call<MutableList<Items>>, response: Response<MutableList<Items>>) {
                _listChanges.value = (response.body() as MutableList<Items>)
            }
        })
    }*/
}