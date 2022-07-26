package com.example.myapplicationapi.ui.MainFragment

import androidx.lifecycle.*
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.data.Repository
import com.example.myapplicationapi.ui.router.Screens
import com.example.myapplicationapi.sortDesc
import com.example.myapplicationapi.sortName
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch



class MainViewModel(
    private val router: Router,
    private val myRepository: Repository,
): ViewModel() {
    // Лист для обновления данных
    private val _list = MutableStateFlow<List<DataBaseModel>>(emptyList())
    val list: Flow<List<DataBaseModel>> = _list
    private var askSort: Int = 0
    private var page = 1
    private var job: Job? = null

    init {
        // Начальная загрузка элементов
        getAllItemList(page)
        // Наблюдатель за изменением базы данных
        observeAllSomething()
    }

    //Открытие фрагмента с детальным просмотром Item'а
    fun routeToDetail(it: DataBaseModel) {
        router.navigateTo(Screens.routeToDetailFragment(it))
    }
    // Наблюдатель за изменением базы данных
    private fun observeAllSomething(){
        job = viewModelScope.launch {
            myRepository.getAllSomethingData().collect{
                _list.value = it
            }
        }
    }

    // Поиск по БД
    fun searchDatabase(searchQuery: String) {
        stopCorutines()
        job = viewModelScope.launch {
            myRepository.searchDataBase(searchQuery).collect{
                _list.value = it
            }
        }
    }

    // Загрузка элементов по средству ретрофита
    private fun getAllItemList(page: Int) {
        job = viewModelScope.launch {
            myRepository.getItem(page)
        }
    }

    // Сортировка по имени, при повторном нажатии по описанию
    fun buttonSortNameOrDesc(){
        stopCorutines()
        if (askSort == sortName)
            askSort = sortDesc
        else askSort = sortName
        job = viewModelScope.launch {
            myRepository.sortByNameOrDesc(askSort).collect {
                _list.value = it
            }
        }
    }

    // Функция для отмены предыдущей корутины, чтобы не создавалось множество потоков при многократном вызове
    private fun stopCorutines(){
        job?.cancel()
        job = null
    }
}
