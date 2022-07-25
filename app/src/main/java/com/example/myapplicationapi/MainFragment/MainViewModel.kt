package com.example.myapplicationapi.MainFragment

import androidx.lifecycle.*
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.Data.Retrofit.Repository
import com.example.myapplicationapi.Screens.Screens
import com.example.myapplicationapi.sortDesc
import com.example.myapplicationapi.sortName
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



class MainViewModel(
    private val router: Router,
    private val myRepository: Repository,
): ViewModel() {

    private val _list = MutableStateFlow<List<DataBaseModel>>(emptyList())
    val list: Flow<List<DataBaseModel>> = _list
    private var askSort: Int = 0
    private var page = 0
    private var job: Job? = null

    init {
        getAllItemList(page)
        observeAllSomething()
    }

    fun routeToDetail(it: DataBaseModel) {
        router.navigateTo(Screens.routeToDetailFragment(it))
    }

    private fun observeAllSomething(){
        stopCorutines()
        job = viewModelScope.launch {
            myRepository.getAllSomethingData().collect{
                _list.value = it
            }
        }
    }

    fun searchDatabase(searchQuery: String) {
        stopCorutines()
        job = viewModelScope.launch {
            myRepository.searchDataBase(searchQuery).collect{
                _list.value = it
            }
        }
    }

    private fun getAllItemList(page: Int) {
        stopCorutines()
        job = viewModelScope.launch {
            if (page <= 10) //это для пагинации, которой пока нет
                myRepository.getItem(page)
        }
    }

    fun observeSortByName(){
        stopCorutines()
        if (askSort == sortName)
            askSort = sortDesc
        else askSort = sortName
        job = viewModelScope.launch {
            myRepository.sortByName(askSort).collect {
                _list.value = it
            }
        }
    }

    private fun stopCorutines(){
        job?.cancel()
        job = null
    }
}
