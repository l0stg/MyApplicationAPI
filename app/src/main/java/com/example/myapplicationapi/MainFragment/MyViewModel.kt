package com.example.myapplicationapi.MainFragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplicationapi.Items
import com.example.myapplicationapi.MyDataClass

class MyViewModel: ViewModel() {

     private val myData: MyDataClass = MyDataClass()

    private val _listChanges = MutableLiveData<List<Items>>()
    val listChanges: LiveData<List<Items>> = _listChanges

    private fun init(){
        myData.adder()
        _listChanges.value = myData.firstList
    }
    init {
        init()
    }
}