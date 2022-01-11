package com.example.recyclerviewkotlinapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object {

        lateinit var retrofit: Retrofit

        fun getRetrofitClint(s: String): Retrofit {
            return if (this::retrofit.isInitialized) {
                retrofit
            } else {
                retrofit = Retrofit.Builder()
                    .baseUrl(s)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit
            }
        }

        /*private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(Url.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: Api by lazy {
            retrofit.create(Api::class.java)
        }*/
    }
}