package com.example.recyclerviewkotlinapplication.room


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(dbHelper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}