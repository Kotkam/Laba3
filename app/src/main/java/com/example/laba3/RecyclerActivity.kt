package com.example.laba3

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laba3.R

import com.example.laba3.Models.Post
import com.example.laba3.Models.Search
import com.example.laba3.Services.PostService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecyclerActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    lateinit var textView: TextView

    val apiKey="4ca8f778"
    var page = 1
    var currentPage : List<Post>? = null
    //lateinit var adapter:CustomRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_movies)
        recyclerView = findViewById(R.id.RecyclerView)
        textView = findViewById(R.id.entity_post)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val intent = intent
        val isPremium = intent.getBooleanExtra("USER_PREMIUM", false)

        getPosts(isPremium)

    }

    fun getPosts(isPremium : Boolean)  {
        val callListPost = PostService.inctance?.JSONApi?.getSearch(apiKey,"Guardians", page)//параметры поиска
        println(callListPost)
        val callback = object : Callback<Search> {
            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                val post = response.body()
                println(post)
                //textView.append("Post Id: ${post!!.search!![0].title}\r\n")
                //textView.append("User Id: ${post.search!![0].year}\r\n")
                //textView.append("Body: ${post.search!![0].type}")

                if(isPremium)
                    currentPage = post!!.search!!
                else
                    currentPage = post!!.search!!.subList(0,5)
                recyclerView.adapter = CustomRecyclerAdapter(currentPage)

            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                textView.append("Something went wrong: ${t.message}")
            }
        }
        callListPost?.enqueue(callback)
    }
}