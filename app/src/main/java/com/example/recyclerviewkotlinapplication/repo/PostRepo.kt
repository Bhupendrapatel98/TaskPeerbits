package com.example.recyclerviewkotlinapplication.repo

import com.example.recyclerviewkotlinapplication.data.Post
import com.example.recyclerviewkotlinapplication.network.Api
import com.example.recyclerviewkotlinapplication.network.RetrofitBuilder
import com.example.recyclerviewkotlinapplication.network.Url

class PostRepo {
    suspend fun getPost(): List<Post> = RetrofitBuilder
        .getRetrofitClint(Url.url)
        .create(Api::class.java)
        .getAllPost()
}