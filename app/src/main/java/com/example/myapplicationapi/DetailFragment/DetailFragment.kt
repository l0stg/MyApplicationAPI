package com.example.myapplicationapi.DetailFragment

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myapplicationapi.Items
import com.example.myapplicationapi.MyDataClass
import com.example.myapplicationapi.R
import com.example.myapplicationapi.Screens.Screens
import com.example.myapplicationapi.databinding.FragmentDetailBinding

class DetailFragment() : Fragment() {

    private var binding: FragmentDetailBinding? = null
    private val screens: Screens = Screens()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemOn = arguments?.getSerializable("Item") as Items

        binding?.tvNameDetail?.text = itemOn.name
        binding?.tvDescriptionDetail?.text = itemOn.description
        with(binding!!) {
            Glide.with(imDetailImage.context)
                .load(itemOn.imageAvatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imDetailImage)
        }

        binding?.imDetailImage?.setOnClickListener{
           screens.routeToDetailImage(itemOn, view)
        }

    }
}


