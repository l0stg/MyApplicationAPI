package com.example.myapplicationapi.MainActivity

import androidx.lifecycle.ViewModel
import com.example.myapplicationapi.Screens.Screens
import com.github.terrakok.cicerone.Router

class MainActivityViewModel(
    private val router: Router
): ViewModel() {
    fun routeToMain() = router.newRootScreen(Screens.routeToMainFragment())
}