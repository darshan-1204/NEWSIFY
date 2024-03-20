package com.example.newsify.Api

import com.example.newsify.Model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("news")
    fun getNews(
        @Query("apikey") apiKey: String = "pub_29355651563b2d57519c342a9ec9590b0b109",
        @Query("language") language: String,
        @Query("country") country: String,
        @Query("category") category: String
    ): Call<NewsResponse>

    @GET("news")
    fun searchNews(
        @Query("apikey") apiKey: String = "pub_29355651563b2d57519c342a9ec9590b0b109",
        @Query("language") language: String,
        @Query("country") country: String,
        @Query("q") category: String
    ): Call<NewsResponse>

}