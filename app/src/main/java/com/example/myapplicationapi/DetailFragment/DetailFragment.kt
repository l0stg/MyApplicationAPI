package com.example.myapplicationapi.DetailFragment

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplicationapi.MyDataClass
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.FragmentDetailBinding

class DetailFragment() : Fragment() {

    private val viewModel by viewModels<DetailViewModel>()
    private var fragmentDemoBinding: FragmentDetailBinding? = null
    private var myData: MyDataClass? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        fragmentDemoBinding = binding
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myData = MyDataClass()
        myData!!.adder()
        fragmentDemoBinding?.tvNameDetail?.text = myData!!.firstList[1].name
        fragmentDemoBinding?.tvDescriptionDetail?.text = myData!!.firstList[1].description
        with(fragmentDemoBinding!!) {
            Glide.with(imDetailImage.context)
                .load(myData!!.firstList[1].imageAvatar)
                .into(imDetailImage)
        }
    }


}
