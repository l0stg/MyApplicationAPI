package com.example.data.di

import androidx.lifecycle.ViewModel
import com.example.data.repositories.SomethingRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataBaseModule = module {
    single { SomethingRepository() }
}