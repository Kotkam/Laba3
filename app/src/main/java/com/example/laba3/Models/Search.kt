package com.example.laba3.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Search {
    @SerializedName("Search")
    @Expose
    var search : List<Post>?=null

    @SerializedName("totalResults")
    @Expose
    var totalResults: String?=null

    @SerializedName("Response")
    @Expose
    var response: String?=null

}