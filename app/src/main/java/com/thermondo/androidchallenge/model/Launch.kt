package com.thermondo.androidchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "launches")
data class Launch(
    @SerializedName("links") var links: Links? = Links(),
    @SerializedName("static_fire_date_utc") var staticFireDateUtc: String? = null,
    @SerializedName("rocket") var rocket: String? = null,
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("details") var details: String? = null,
    @SerializedName("launchpad") var launchpad: String? = null,
    @SerializedName("auto_update") var autoUpdate: Boolean? = null,
    @SerializedName("flight_number") var flightNumber: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("date_utc") var dateUtc: String? = null,
    @SerializedName("date_unix") var dateUnix: Int? = null,
    @SerializedName("date_local") var dateLocal: String? = null,
    @SerializedName("date_precision") var datePrecision: String? = null,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") var id: String? = null,

    var isBookmarked: Boolean = false

)