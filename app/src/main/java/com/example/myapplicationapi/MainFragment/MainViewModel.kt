package com.example.myapplicationapi.MainFragment

import androidx.lifecycle.*
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.Data.Retrofit.Repository
import com.example.myapplicationapi.Screens.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch


class MainViewModel(
    private val router: Router,
    private val myRepository: Repository,
): ViewModel() {

    private val _list: MutableLiveData<List<DataBaseModel>> = MutableLiveData()
    val list: LiveData<List<DataBaseModel>> = _list
    private var askSort: Int = 0

    init {
        getAllItemList(1)
    }

    fun routeToDetail(it: DataBaseModel) {
        router.navigateTo(Screens.routeToDetailFragment(it))
    }

    fun observeAllSomething(): LiveData<List<DataBaseModel>> =
        myRepository.updateData()

    fun searchDatabase(searchQuery: String): LiveData<List<DataBaseModel>> =
        myRepository.searchDataBase(searchQuery)


    private fun getAllItemList(page: Int) {
        viewModelScope.launch {
            if (page <= 10) //это для пагинации, которой пока нет
                myRepository.getItem(page)
        }
    }

    fun observeSortByName(): LiveData<List<DataBaseModel>>{
        askSort = if (askSort == 0) //это криво давай нормально сделаем
            1
        else 0
        return myRepository.sortByName(askSort)
    }
}
