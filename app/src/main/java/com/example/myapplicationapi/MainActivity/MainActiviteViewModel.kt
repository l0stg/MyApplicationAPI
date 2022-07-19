package com.example.myapplicationapi.MainActivity

import androidx.lifecycle.ViewModel
import com.example.myapplicationapi.Screens.Screens

class MainActivityViewModel: ViewModel() {
    fun routeToMain() = Screens.routeToMainFragment()
}