package com.example.myapplicationapi.DetailFragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.myapplicationapi.Items
import com.example.myapplicationapi.R

class DetailViewModel: ViewModel() {

    fun routeToDetailImage(item: Items, view: View) {
        val bundle = Bundle()
        bundle.putString("Image", item.imageAvatar)
        view.findNavController().navigate(R.id.action_detailFragment_to_imageFragment, bundle)
    }

}