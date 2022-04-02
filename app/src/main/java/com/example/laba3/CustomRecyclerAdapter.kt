package com.example.laba3

import android.content.Intent
import android.view.LayoutInflater
import com.example.laba3.Models.Post
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomRecyclerAdapter (var films: List<Post>?):RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>(){

    private lateinit var mListener:onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }    

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var element: TextView? = null

        init {
            element = itemView.findViewById(R.id.element)
            //itemView.setOnClickListener { listner.onItemClick(adapterPosition) }
        }

    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.element, parent, false)
            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            //holder.element?.text = "Post Id: ${films[position].title}"
            holder.element?.append("Title: ${films!![position].title}\r\n")
            holder.element?.append("Year: ${films!![position].year}\r\n")
            holder.element?.append("Type: ${films!![position].type}")
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailedActivity::class.java)
                intent.putExtra("title", films!![position].title)
                holder.itemView.context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return films!!.size
        }
}