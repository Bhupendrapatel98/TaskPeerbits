package com.example.recyclerviewkotlinapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(

    //@PrimaryKey(autoGenerate = true)
   // val id: Long,

    val name: String,
    val age: Int,
    val destination: String,
){
    @PrimaryKey(autoGenerate = true)
    var id:Long?=null
}