package com.example.recyclerviewkotlinapplication.room

interface DatabaseHelper {

    suspend fun getUsers() : List<User>

    suspend fun insertUser(users: List<User>)
}