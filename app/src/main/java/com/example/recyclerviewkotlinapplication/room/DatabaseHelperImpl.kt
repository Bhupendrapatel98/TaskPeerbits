package com.example.recyclerviewkotlinapplication.room

class DatabaseHelperImpl(private val appDatabase: UserDatabase) : DatabaseHelper {

    override suspend fun getUsers(): List<User> = appDatabase.UserDao().getUser()

    override suspend fun insertUser(users: List<User>) = appDatabase.UserDao().insertUser(users)
}