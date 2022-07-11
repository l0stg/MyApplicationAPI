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
import com.example.myapplicationapi.databinding.FragmentDetailBinding

class DetailFragment() : Fragment() {

    private val viewModel by viewModels<DetailViewModel>()
    private var fragmentDemoBinding: FragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        fragmentDemoBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemOn = arguments?.getSerializable("Item") as Items

        fragmentDemoBinding?.tvNameDetail?.text = itemOn.name
        fragmentDemoBinding?.tvDescriptionDetail?.text = itemOn.description
        with(fragmentDemoBinding!!) {
            Glide.with(imDetailImage.context)
                .load(itemOn.imageAvatar)
                .into(imDetailImage)
        }

        fragmentDemoBinding?.imDetailImage?.setOnClickListener{
           viewModel.routeToDetailImage(itemOn, view)
        }

    }
}


