package com.example.myapplicationapi.ui.MainActivity

import androidx.lifecycle.ViewModel
import com.example.myapplicationapi.router.Screens
import com.github.terrakok.cicerone.Router

class MainActivityViewModel(
    private val router: Router
): ViewModel() {
    // Открытие главного экрана
    fun routeToMain() = router.newRootScreen(Screens.routeToMainFragment())
}