package com.thermondo.androidchallenge.model

import com.google.gson.annotations.SerializedName


data class Patch (

  @SerializedName("small" ) var small : String? = null,
  @SerializedName("large" ) var large : String? = null

)