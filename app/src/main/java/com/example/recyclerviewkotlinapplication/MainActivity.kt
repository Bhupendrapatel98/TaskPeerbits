package com.example.recyclerviewkotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewkotlinapplication.adapter.PostAdapter
import com.example.recyclerviewkotlinapplication.data.Post
import com.example.recyclerviewkotlinapplication.databinding.ActivityMainBinding
import com.example.recyclerviewkotlinapplication.repo.PostRepo
import com.example.recyclerviewkotlinapplication.viewModel.PostViewModel
import com.example.recyclerviewkotlinapplication.viewModel.PostViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var postAdapter: PostAdapter
    private lateinit var postViewModel: PostViewModel
    private lateinit var postViewModelFactory: PostViewModelFactory
    private lateinit var postRepository: PostRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        postRepository = PostRepo()
        postViewModelFactory = PostViewModelFactory(postRepository)
        postViewModel = ViewModelProvider(this, postViewModelFactory).get(PostViewModel::class.java)
        postViewModel.getPost()

        postViewModel.myResponse.observe(this, Observer {
            postAdapter.setPostData(it as ArrayList<Post>)
        })

        postAdapter = PostAdapter(this, ArrayList())
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = postAdapter
        }

    }
}