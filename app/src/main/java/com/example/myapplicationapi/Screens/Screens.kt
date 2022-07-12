package com.example.myapplicationapi.Screens

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.example.myapplicationapi.Items
import com.example.myapplicationapi.R

class Screens {

    fun transToItem(item: Items): Bundle {
        val bundle = Bundle()
        bundle.putSerializable("Item",item)
        return (bundle)
    }

    fun routeToDetailFragment(item: Items, view: View){
        view.findNavController().navigate(R.id.action_mainFragment_to_detailFragment, transToItem(item))
    }

    fun routeToDetailImage(item: Items, view: View) {
        view.findNavController().navigate(R.id.action_detailFragment_to_imageFragment, transToItem(item))
    }


}