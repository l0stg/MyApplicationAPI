package com.example.myapplicationapi.imageViewFargment

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.example.data.models.DataBaseModel
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.FragmentImageBinding
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ImageFragment : Fragment() {

    private var binding: FragmentImageBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagePut = arguments?.getSerializable("Item") as String //вынести во вью модел

        with(binding!!) {
            Glide.with(imageDetail.context)
                .load(imagePut)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageDetail)
        }

        binding!!.saveOnDeviceButton.setOnClickListener {
            downloadImageFromPath(imagePut)
            Toast.makeText(activity, "Изображение сохранено", Toast.LENGTH_SHORT).show()
        }
    }

    private fun downloadImageFromPath(name: String) {
        val image = binding!!.imageDetail
        val imageBitmap = image.drawable.toBitmap()
        MediaStore.Images.Media.insertImage(
            requireContext().contentResolver,
            imageBitmap,
            "$name",
            null)
    }
}