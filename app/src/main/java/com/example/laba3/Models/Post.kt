package com.example.laba3.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("Title")
    @Expose
    var title :String? = null

    @SerializedName("Year")
    @Expose
    var year: String?=null

    @SerializedName("imdbID")
    @Expose
    var imdbID: String? = null

    @SerializedName("Type")
    @Expose
    var type: String? = null
}