package com.example.recyclerviewkotlinapplication.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: List<User>)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM User" )
    suspend fun getUser(): List<User>
}