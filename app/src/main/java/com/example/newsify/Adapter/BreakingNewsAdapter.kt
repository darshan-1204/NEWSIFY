package com.example.newsify.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsify.Model.Result
import com.example.newsify.Activity.NewsDetailActivity
import com.example.newsify.R


class BreakingNewsAdapter(articles: List<Result>?) : RecyclerView.Adapter<BreakingNewsAdapter.DataHolder>() {

    var data = articles

    class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var img = itemView.findViewById<ImageView>(R.id.b_newsImage)
        var title = itemView.findViewById<TextView>(R.id.b_newsTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        return DataHolder(LayoutInflater.from(parent.context).inflate(R.layout.b_news_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        Glide.with(holder.itemView.context).load(data!![position].image_url).into(holder.img)
        holder.title.text = data!![position].title

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