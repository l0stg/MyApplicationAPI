package com.example.data.di

import androidx.room.Room
import com.example.data.Provider
import org.koin.dsl.module

val dataBaseModule = module {

    // Сингл Дао
    single { Provider.instance?.somethingDao() }
    single { Provider.instance }
}