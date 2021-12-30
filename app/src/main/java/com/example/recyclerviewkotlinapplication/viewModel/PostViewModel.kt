package com.example.recyclerviewkotlinapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerviewkotlinapplication.data.Post
import com.example.recyclerviewkotlinapplication.repo.PostRepo
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository: PostRepo) : ViewModel() {

    val myResponse: MutableLiveData<List<Post>> = MutableLiveData()

    fun getPost() {
        viewModelScope.launch {
            val response = postRepository.getPost()
            myResponse.value = response
        }
    }
}