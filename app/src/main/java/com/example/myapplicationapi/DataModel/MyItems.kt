package com.example.myapplicationapi.DataModel


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Items(
    var name: String? = null,
    var description: String? = null,
    @SerializedName("image_url")
    var imageAvatar: String? = null
): Serializable
