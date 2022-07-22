package com.example.myapplicationapi.di

import com.example.myapplicationapi.Data.Retrofit.Common
import com.example.myapplicationapi.Data.Retrofit.RetrofitServices
import com.example.myapplicationapi.DetailFragment.DetailViewModel
import com.example.myapplicationapi.MainActivity.MainActivityViewModel
import com.example.myapplicationapi.MainFragment.MainViewModel
import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    val cicerone = Cicerone.create()
    single{cicerone.router}
    single{cicerone.getNavigatorHolder()}
    single{Common()}

    viewModel{ MainActivityViewModel(get()) }
    viewModel{ DetailViewModel(get()) }
    viewModel{ MainViewModel(get(), get(), get()) }


}