package com.example.myapplicationapi


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyDataClass {

}

data class Items(
    val name: String? = null,
    val description: String? = null,
    @SerializedName("image_url")
    val imageAvatar: String? = null
    ): Serializable



