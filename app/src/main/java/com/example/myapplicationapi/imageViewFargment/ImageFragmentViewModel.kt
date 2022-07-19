package com.example.myapplicationapi.imageViewFargment

import android.content.ContentResolver
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.insertImage
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModel


class ImageFragmentViewModel: ViewModel() {
    fun downloadImageFromPath(context: ContentResolver, image: ImageView) {
        val imageBitmap = image.drawable.toBitmap()
        insertImage(
            context,
            imageBitmap,
            "name",
            null)
    }

}