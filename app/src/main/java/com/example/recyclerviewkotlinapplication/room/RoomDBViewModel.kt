package com.example.recyclerviewkotlinapplication.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RoomDBViewModel (private val dbHelper: DatabaseHelper) : ViewModel() {

    private val users = MutableLiveData<List<User>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {

               // val usersToInsertInDB = mutableListOf<User>()

                val usersFromDb = dbHelper.getUsers()

                //usersToInsertInDB.add(User(2,"Ankit",23,"Bhopal"))

                //dbHelper.insertUser(usersToInsertInDB)

                //users.postValue(usersToInsertInDB)
                users.postValue(usersFromDb)

            } catch (e: Exception) {
                //users.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    suspend fun insertUsers(name:String,age:Int,desc:String) {

        val usersToInsertInDB = mutableListOf<User>()

        usersToInsertInDB.add(User(name,age,desc))

        dbHelper.insertUser(usersToInsertInDB)
    }

    fun getUsers(): LiveData<List<User>> {
        return users
    }
}