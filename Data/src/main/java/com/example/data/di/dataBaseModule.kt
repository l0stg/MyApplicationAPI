package com.example.data.di

import com.example.data.repositories.SomethingRepository
import org.koin.dsl.module

val dataBaseModule = module {
    single { SomethingRepository() }
}