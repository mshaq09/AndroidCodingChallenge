package com.thermondo.androidchallenge.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.thermondo.androidchallenge.model.Links

class SpaceXTypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromLinks(link: Links? = null) = gson.toJson(link)

    @TypeConverter
    fun toLinks(json: String) = gson.fromJson(json, Links::class.java)


}