package com.example.myapplicationapi.MainFragment

import androidx.lifecycle.*
import com.example.data.models.DataBaseModel
import com.example.data.repositories.SomethingRepository
import com.example.myapplicationapi.Data.Retrofit.Repository
import com.example.myapplicationapi.Screens.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.launch


class MainViewModel(
    private val router: Router,
    private val mService: Repository,
    private val somRep: SomethingRepository
): ViewModel() {

    private var askSort: Int = 0

    init {
        getAllItemList(1)
    }

    fun routeToDetail(it: DataBaseModel) {
        router.navigateTo(Screens.routeToDetailFragment(it))
    }

    fun observeAllSomething(): LiveData<List<DataBaseModel>> =
        somRep.getAllSomethingData().asLiveData()

    fun searchDatabase(searchQuery: String): LiveData<List<DataBaseModel>>
         = somRep.searchDataBase(searchQuery).asLiveData()

    private fun getAllItemList(page: Int) {
        viewModelScope.launch {
            if (page <= 10)
                mService.getItem(page)
        }
    }

    fun observeSortByName(): LiveData<List<DataBaseModel>>{
        askSort = if (askSort == 0) //это криво давай нормально сделаем
            1
        else 0
        return somRep.sortByName(askSort).asLiveData()
    }
}
