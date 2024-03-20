package com.example.newsify.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsify.Activity.NewsDetailActivity
import com.example.newsify.Model.Result
import com.example.newsify.R

class SearchNewsAdapter(data2: List<Result>?) : Adapter<SearchNewsAdapter.DataHolder>() {

    var data = data2

    class DataHolder(itemView: View) : ViewHolder(itemView){
        var txt = itemView.findViewById<TextView>(R.id.rcvText)
        var img = itemView.findViewById<ImageView>(R.id.rcvImg)
        var source = itemView.findViewById<TextView>(R.id.rcvSource)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        return DataHolder(LayoutInflater.from(parent.context).inflate(R.layout.news_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        holder.txt.text = data?.get(position)?.title
        holder.source.text = data!![position].source_id

        Glide.with(holder.itemView.context).load(data?.get(position)?.image_url).into(holder.img)

        holder.itemView.setOnClickListener {
            var intent = Intent(holder.itemView.context, NewsDetailActivity::class.java)
            intent.putExtra("content", data!![position].content)
            intent.putExtra("title", data!![position].title)
            intent.putExtra("description", data!![position].description)
            intent.putExtra("img", data!![position].image_url)
            intent.putExtra("link", data!![position].link)
            holder.itemView.context.startActivity(intent)
        }
    }
}