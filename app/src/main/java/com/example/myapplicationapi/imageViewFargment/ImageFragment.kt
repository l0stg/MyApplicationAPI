package com.example.myapplicationapi.imageViewFargment

import android.content.ContentResolver
import android.os.Bundle
import android.provider.MediaStore.Images.Media.insertImage
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.FragmentImageBinding

class ImageFragment : Fragment(R.layout.fragment_image) {

    private val binding: FragmentImageBinding by viewBinding()
    private val viewModel: ImageFragmentViewModel by viewModels()

    companion object{
        private const val PHOTO = "PHOTO"
        fun newInstance(url: String) = ImageFragment().apply {
            arguments = Bundle().apply {
                putString(PHOTO, url)
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val urlPhoto = arguments?.getString(PHOTO)//вынести во вью модел

        with(binding) {
            Glide.with(imageDetail.context)
                .load(urlPhoto)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageDetail)
            saveOnDeviceButton.setOnClickListener {
                val image = imageDetail
                viewModel.downloadImageFromPath(requireContext().contentResolver, image)
                Toast.makeText(activity, "Изображение сохранено", Toast.LENGTH_SHORT).show()
            }

        }
    }
}