package com.example.myapplicationapi.App

import android.app.Application
import com.example.data.Provider

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Provider.create(this)
    }
}