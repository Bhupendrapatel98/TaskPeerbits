package com.example.recyclerviewkotlinapplication.repo

import com.example.recyclerviewkotlinapplication.data.Post
import com.example.recyclerviewkotlinapplication.network.RetrofitBuilder

class PostRepo {
    suspend fun getPost(): List<Post> = RetrofitBuilder.api.getAllPost()
}