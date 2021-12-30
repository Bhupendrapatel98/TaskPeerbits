package com.example.recyclerviewkotlinapplication.network

import com.example.recyclerviewkotlinapplication.data.Post
import retrofit2.http.GET

interface Api {

    @GET("posts")
    suspend fun getAllPost(): List<Post>
}