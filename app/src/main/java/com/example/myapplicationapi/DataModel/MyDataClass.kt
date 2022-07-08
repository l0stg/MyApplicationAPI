package com.example.myapplicationapi

import java.io.Serializable

class MyDataClass {

    val firstList: MutableList<Items> = mutableListOf()

    fun adder(){
        var item = Items("Buzz","A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.", "https://images.punkapi.com/v2/keg.png")
        firstList.add(item)
        item = Items("Buzz","A light, crisp and bitter IPA brewed with English and American hops. A small batch brewed only once.", "https://images.punkapi.com/v2/keg.png")
        firstList.add(item)
        item = Items("1","A light, crisp and bitter IPA ", "")
        firstList.add(item)
        item = Items("Buzz","A light,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "https://images.punkapi.com/v2/keg.png")
        firstList.add(item)
        item = Items("Buzz","A light,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "https://images.punkapi.com/v2/keg.png")
        firstList.add(item)
        item = Items("Buzz","A light,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "https://images.punkapi.com/v2/keg.png")
        firstList.add(item)
        item = Items("Buzz","A light,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "https://images.punkapi.com/v2/keg.png")
        firstList.add(item)
        item = Items("Buzz","A light,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "https://images.punkapi.com/v2/keg.png")
        firstList.add(item)
        item = Items("Buzz","A light,aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "https://images.punkapi.com/v2/keg.png")
        firstList.add(item)
    }
}

data class Items(
    val name: String,
    val description: String,
    val imageAvatar: String
    ):Serializable

