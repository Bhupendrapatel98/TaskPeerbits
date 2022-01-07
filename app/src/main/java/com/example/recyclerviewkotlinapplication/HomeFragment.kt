package com.example.recyclerviewkotlinapplication

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewkotlinapplication.adapter.PostAdapter
import com.example.recyclerviewkotlinapplication.data.Post
import com.example.recyclerviewkotlinapplication.network.Url
import com.example.recyclerviewkotlinapplication.repo.PostRepo
import com.example.recyclerviewkotlinapplication.room.DatabaseBuilder
import com.example.recyclerviewkotlinapplication.room.DatabaseHelperImpl
import com.example.recyclerviewkotlinapplication.room.RoomDBViewModel
import com.example.recyclerviewkotlinapplication.room.ViewModelFactory
import com.example.recyclerviewkotlinapplication.viewModel.PostViewModel
import com.example.recyclerviewkotlinapplication.viewModel.PostViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.ArrayList

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val userViewModel: RoomDBViewModel by viewModels{
        ViewModelFactory(DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext())))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController(view)

        submit_button.setOnClickListener{

            GlobalScope.launch {
                userViewModel.insertUsers(name.text.toString(),age.text.toString().toInt(),destination.text.toString())
            }

            /*val intent = Intent(context,MainActivity::class.java)
            intent.putExtra("name",name.text.toString())
            intent.putExtra("age",age.text.toString())
            intent.putExtra("destination",destination.text.toString())
            startActivity(intent)*/
        }

    }
}

