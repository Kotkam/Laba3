package com.example.laba3.Services

import com.example.laba3.JSONPlaceholderApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostService {
    private val mRetforit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    val JSONApi: JSONPlaceholderApi
        get() = mRetforit.create(JSONPlaceholderApi::class.java)

    companion

    object {
        private var mInstance: PostService? = null
        private const val BASE_URL = "http://www.omdbapi.com/"
        val inctance: PostService?
            get() {
                if (mInstance == null) {
                    mInstance = PostService()
                }
                return mInstance;
            }
    }
}