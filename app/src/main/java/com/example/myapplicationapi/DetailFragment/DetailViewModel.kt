package com.example.myapplicationapi.DetailFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.Screens.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val router: Router
): ViewModel() {

    private val _imageList =  MutableStateFlow<List<String>>(emptyList()) //заменить на флоу
    val imageList: Flow<List<String>> = _imageList

    fun routeToImageFragment(url: String) = router.navigateTo(Screens.routeToImageFragment(url))

    fun addToList(item: DataBaseModel) {
        viewModelScope.launch {
            val list: MutableList<String> = mutableListOf()
            repeat(5){
                list.add(item.imageAvatar.toString())
            }
            list.add("")//проверка на работающую заглушку
            _imageList.value = list
        }
    }

}