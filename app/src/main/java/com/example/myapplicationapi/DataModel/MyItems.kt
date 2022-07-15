package com.example.myapplicationapi.DataModel


import com.google.gson.annotations.SerializedName
import java.io.Serializable

var intResponse: Int = 1

data class Items(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    @SerializedName("image_url")
    val imageAvatar: String? = null
): Serializable
