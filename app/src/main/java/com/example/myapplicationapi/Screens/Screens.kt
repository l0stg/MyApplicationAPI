package com.example.myapplicationapi.Screens

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.DetailFragment.DetailFragment
import com.example.myapplicationapi.MainFragment.MainFragment
import com.example.myapplicationapi.R
import com.example.myapplicationapi.imageViewFargment.ImageFragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun routeToMainFragment() = FragmentScreen { MainFragment() }
    fun routeToDetailFragment(item: DataBaseModel) = FragmentScreen { DetailFragment.newInstance(item) }
    fun routeToImageFragment(url: String) = FragmentScreen { ImageFragment.newInstance(url) }
}