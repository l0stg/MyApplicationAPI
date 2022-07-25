package com.example.data.di

import com.example.data.Provider
import org.koin.dsl.module

val dataBaseModule = module {
    single { Provider.instance?.somethingDao() }
}