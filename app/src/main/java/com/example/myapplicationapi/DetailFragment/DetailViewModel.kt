package com.example.myapplicationapi.DetailFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.Screens.Screens

class DetailViewModel: ViewModel() {

    private val _imageList: MutableLiveData<List<String>> = MutableLiveData()
    val imageList: LiveData<List<String>> = _imageList

    fun routeToImageFragment(url: String) = Screens.routeToImageFragment(url)

    fun addToList(item: DataBaseModel) {
        val list: MutableList<String> = mutableListOf()
        repeat((1..5).count()) {
            list.add(item.imageAvatar.toString())
        }
        _imageList.value = list
    }

}