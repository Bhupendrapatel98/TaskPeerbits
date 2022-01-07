package com.example.recyclerviewkotlinapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewkotlinapplication.adapter.PostAdapter
import com.example.recyclerviewkotlinapplication.adapter.UserAdapter
import com.example.recyclerviewkotlinapplication.data.Post
import com.example.recyclerviewkotlinapplication.room.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyDetailsFragment : Fragment(R.layout.fragment_detail) {

    private val userViewModel: RoomDBViewModel by viewModels{
        ViewModelFactory(DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext())))
    }

    private lateinit var userAdapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        userViewModel.getUsers().observe(viewLifecycleOwner, Observer {
            Log.d("shdbdjhbdd", "onViewCreated: "+it)
           // text.text = it.get(0).name
            userAdapter.setPostData(it as ArrayList<User>)
        })

        userAdapter = UserAdapter(requireContext(), ArrayList())
        recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }

    }

}

