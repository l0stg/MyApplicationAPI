package com.example.myapplicationapi.MainFragment

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationapi.Data.Retrofit.Common
import com.example.myapplicationapi.Data.Retrofit.RetrofitServices
import com.example.myapplicationapi.Items
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MyViewModel: ViewModel() {

    private val _listChanges = MutableLiveData<List<Items>>()
    val listChanges: LiveData<List<Items>> = _listChanges
    private var mService: RetrofitServices = Common.retrofitService

    private fun init(){
        getAllMovieList()
    }
    init {
        init()
    }

    private fun getAllMovieList() {
        mService.getMovieList().enqueue(object : Callback<MutableList<Items>> {
            override fun onFailure(call: Call<MutableList<Items>>, t: Throwable) {
            }
            override fun onResponse(call: Call<MutableList<Items>>, response: Response<MutableList<Items>>) {
                _listChanges.value = (response.body() as MutableList<Items>)
            }
        })
    }
    //сортировка по имени
    fun sortByName(){
        _listChanges.value = _listChanges.value!!.sortedBy { it.name }
    }

/*    fun filterList(p0: String?) {
        val displayList: MutableList<Items> = mutableListOf()
        if (p0?.isNotEmpty() == true){
        val search = p0.toLowerCase(Locale.getDefault())
        _listChanges.value?.forEach {
            if (it.name?.toLowerCase(Locale.getDefault()).toString().contains(search))
                displayList.clear()
                displayList.add(it)

            }
            _listChanges.value = displayList.toMutableList()
        }else{
            displayList.clear()
            displayList.addAll(_listChanges.value!!)
            _listChanges.value = displayList.toMutableList()
        }

    }*/

}