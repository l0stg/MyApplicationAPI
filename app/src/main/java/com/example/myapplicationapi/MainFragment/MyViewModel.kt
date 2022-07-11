package com.example.myapplicationapi.MainFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myapplicationapi.Data.Retrofit.Common
import com.example.myapplicationapi.Data.Retrofit.RetrofitServices
import com.example.myapplicationapi.Items
import com.example.myapplicationapi.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel: ViewModel() {

    private val _listChanges = MutableLiveData<List<Items>>()
    val listChanges: LiveData<List<Items>> = _listChanges
    private var mService: RetrofitServices = Common.retrofitService

    fun routeToDetailFragment(item: Items, view: View){
        val bundle = Bundle()
        bundle.putSerializable("Item", item)
        view.findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
    }

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
}