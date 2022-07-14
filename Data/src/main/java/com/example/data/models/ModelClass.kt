package com.example.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "my_table")
data class DataBaseModel(
    @PrimaryKey var uuid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") var name: String,
    @ColumnInfo(name = "subtitle") var description: String,
    @ColumnInfo(name = "image") var imageAvatar: String,
    ): Serializable
