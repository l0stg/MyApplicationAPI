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
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    private fun transToItem(item: DataBaseModel): Bundle {
        val bundle = Bundle()
        bundle.putSerializable("Item",item)
        return (bundle)
    }

    fun routeToMainFragment() = router.newRootScreen( FragmentScreen { MainFragment() })
    fun routeToDetailFragment(item: DataBaseModel) = router.navigateTo(FragmentScreen { DetailFragment.newInstance(item) })
    fun routeToImageFragment(url: String) = router.navigateTo(FragmentScreen {ImageFragment.newInstance(url)})




}