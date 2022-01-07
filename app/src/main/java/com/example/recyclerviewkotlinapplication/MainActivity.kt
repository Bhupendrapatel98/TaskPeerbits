package com.example.recyclerviewkotlinapplication

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
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
import android.graphics.Color
import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.recyclerviewkotlinapplication.network.Url.url
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var postAdapter: PostAdapter
    private lateinit var postViewModel: PostViewModel
    private lateinit var postViewModelFactory: PostViewModelFactory
    private lateinit var postRepository: PostRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.userAge.text = "Age : " + intent.getStringExtra("age").toString()
        binding.userDest.text = "Destination : " +intent.getStringExtra("destination").toString()
        binding.userName.text = "Name : "+intent.getStringExtra("name").toString()

        binding.collapsingToolbarLayout.title = "Test Demo"

        binding.fab.setOnClickListener {
            customIntent()
        }

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

    private fun getItem(): PendingIntent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(
            Intent.EXTRA_SUBJECT,
            "Droidmentor is a site, which contains android tutorials"
        )
        shareIntent.putExtra(Intent.EXTRA_TEXT, url)
        return PendingIntent.getActivity(this, 0, shareIntent, 0)
    }

    fun customIntent() {
        val url: String = "https://www.tutorialspoint.com/"
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()

        //addressbar color
        val colorInt = Color.parseColor("#FF0000") //red
        val defaultColors: CustomTabColorSchemeParams = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(colorInt)
            .build()
        builder.setDefaultColorSchemeParams(defaultColors)

        // Change close icon
        AppCompatResources.getDrawable(this, R.drawable.ic_back)?.let {
            DrawableCompat.setTint(it, Color.WHITE)
            builder.setCloseButtonIcon(it.toBitmap())
        }

        // Set the action button
        AppCompatResources.getDrawable(this, R.drawable.ic_add)?.let {
            DrawableCompat.setTint(it, Color.WHITE)
            builder.setActionButton(
                it.toBitmap(),
                "Bhupendra",
                getItem(),
                false
            )
        }

        //add menu item
        builder.addMenuItem("data", getItem())

        val customTabsIntent: CustomTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}