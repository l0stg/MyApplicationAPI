package com.example.myapplicationapi


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyDataClass {

}

data class Items(
    val name: String,
    val description: String,
    @SerializedName("image_url")
    val imageAvatar: String
    ): Serializable


