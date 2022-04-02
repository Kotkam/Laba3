package com.example.laba3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.laba3.Models.DetailedPost
import com.example.laba3.Models.Search
import com.example.laba3.Services.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailedActivity: AppCompatActivity()  {
    val apiKey="4ca8f778"
    lateinit var titleText :TextView
    lateinit var yearText :TextView
    lateinit var genreText :TextView
    lateinit var plotText :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detailed_info)

        titleText=findViewById(R.id.titleText)
        yearText=findViewById(R.id.yearText)
        genreText=findViewById(R.id.genreText)
        plotText=findViewById(R.id.plotText)
        getDetailedInfo()
    }

    fun getDetailedInfo(){
        val callListPost = PostService.inctance?.JSONApi?.getDetailedInfo(apiKey,intent.extras!!.get("title").toString())//параметры поиска
        println(callListPost)
        val callback = object : Callback<DetailedPost> {
            override fun onResponse(call: Call<DetailedPost>, response: Response<DetailedPost>) {
                val post = response.body()
                println(post)
                titleText.append("Title: ${post!!.title}")
                yearText.append("Yeat: ${post!!.year}")
                genreText.append("Genre: ${post!!.genre}")
                plotText.append("Plot:${post!!.plot}")
            }

            override fun onFailure(call: Call<DetailedPost>, t: Throwable) {
                titleText.append("Something went wrong: ${t.message}")
            }
        }
        callListPost?.enqueue(callback)
    }
}