package com.example.myapplicationapi.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import by.kirich1409.viewbindingdelegate.viewBinding

import com.example.myapplicationapi.R
import com.example.myapplicationapi.Screens.Screens
import com.example.myapplicationapi.Screens.Screens.navigatorHolder

import com.example.myapplicationapi.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.routeToMain()
    }

    private val navigator = AppNavigator(this, R.id.fragmentContainerView)

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

}