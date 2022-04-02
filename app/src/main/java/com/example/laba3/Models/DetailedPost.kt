package com.example.laba3.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailedPost {
    @SerializedName("Title")
    @Expose
    var title:String?=null

    @SerializedName("Year")
    @Expose
    var year:String?=null

    @SerializedName("Genre")
    @Expose
    var genre:String?=null

    @SerializedName("Plot")
    @Expose
    var plot:String?=null
}