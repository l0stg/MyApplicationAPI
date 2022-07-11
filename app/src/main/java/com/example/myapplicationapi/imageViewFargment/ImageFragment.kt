package com.example.myapplicationapi.imageViewFargment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplicationapi.Items
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.FragmentDetailBinding
import com.example.myapplicationapi.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private var fragmentImageBinding: FragmentImageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentImageBinding.inflate(inflater, container, false)
        fragmentImageBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagePut = arguments?.getString("Image")//вынести во вью модел

        println(imagePut)

        with(fragmentImageBinding!!) {
            Glide.with(imageDetail.context)
                .load(imagePut)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageDetail)
        }
    }

}