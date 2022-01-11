package com.example.recyclerviewkotlinapplication.network

import com.example.recyclerviewkotlinapplication.data.NotificationData
import com.example.recyclerviewkotlinapplication.data.Post
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @GET("posts")
    suspend fun getAllPost(): List<Post>

    @POST("send")
    fun send(
        @Header("Accept") Accept: String,
        @Header("Content") Content: String,
        @Header("Authorization") key: String,
        @Body notificationData: NotificationData): Call<JsonObject>
}