package com.example.laba3


import com.example.laba3.Models.DetailedPost
import com.example.laba3.Models.Post
import com.example.laba3.Models.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JSONPlaceholderApi {
    @GET("?")
    fun getDetailedInfo(@Query("apikey") apiKey:String, @Query("t") Title:String): Call<DetailedPost>

    @GET("?")
    fun getSearch(@Query("apikey") apiKey:String, @Query("s") searchTitle:String, @Query("page") page:Int): Call<Search>

    //Получение по году по тайтлу и 1 фильм
}