package com.example.myapplicationapi.di


import com.example.myapplicationapi.data.Repository
import com.example.myapplicationapi.data.Retrofit.RetrofitClient
import com.example.myapplicationapi.data.Retrofit.RetrofitServices
import com.example.myapplicationapi.ui.DetailFragment.DetailViewModel
import com.example.myapplicationapi.ui.MainActivity.MainActivityViewModel
import com.example.myapplicationapi.ui.MainFragment.MainViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    val cicerone = Cicerone.create()

    // Сингл для Cicerone
    single{cicerone.router}
    single{cicerone.getNavigatorHolder()}

    // Синг для репозитория, инжектим ретрофит и дао
    single{ Repository(get(), get()) }

    // Сингл ретрофита
    single { RetrofitClient.api}

    // ViewModel

    // Инжектим Роутер
    viewModel{ MainActivityViewModel(get()) }

    // Инжектим Роутер
    viewModel{ DetailViewModel(get()) }

    // Инжектим роутер и репозиторий
    viewModel{ MainViewModel(get(), get()) }


}