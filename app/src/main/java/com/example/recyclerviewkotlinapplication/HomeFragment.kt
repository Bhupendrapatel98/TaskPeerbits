package com.example.recyclerviewkotlinapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.example.recyclerviewkotlinapplication.data.Data
import com.example.recyclerviewkotlinapplication.data.NotificationData
import com.example.recyclerviewkotlinapplication.network.Api
import com.example.recyclerviewkotlinapplication.network.RetrofitBuilder
import com.example.recyclerviewkotlinapplication.network.Url
import com.example.recyclerviewkotlinapplication.room.DatabaseBuilder
import com.example.recyclerviewkotlinapplication.room.DatabaseHelperImpl
import com.example.recyclerviewkotlinapplication.room.RoomDBViewModel
import com.example.recyclerviewkotlinapplication.room.ViewModelFactory
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val userViewModel: RoomDBViewModel by viewModels{
        ViewModelFactory(DatabaseHelperImpl(DatabaseBuilder.getInstance(requireContext())))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController(view)

        submit_button.setOnClickListener{

            sendNotification()

            //room
           /* GlobalScope.launch {
                userViewModel.insertUsers(name.text.toString(),age.text.toString().toInt(),destination.text.toString())
            }*/

            /*val intent = Intent(context,MainActivity::class.java)
            intent.putExtra("name",name.text.toString())
            intent.putExtra("age",age.text.toString())
            intent.putExtra("destination",destination.text.toString())
            startActivity(intent)*/
        }

    }

    fun sendNotification(){

        val data = Data("Bhupendra","hiiiiiii")

        val notificationData = NotificationData("/topics/Bhup",data,)

        with(RetrofitBuilder) {

            getRetrofitClint(Url.Fcm_url)
                .create(Api::class.java)
                .send("application/json","application/json",
                    "key=AAAA3He4Few:APA91bG7MPMaJmiXq6pe9yruCSzkIhV-FREDF42_hCgjGnvnYJWPavieIqAseHwfnSpbLpxFvdbPCIggRIwHY-K6eNgAfvghOgtuo8EVyrPIfbgksE6AMtVjDochQLvuTQu1gCVFjOHk",
                    notificationData)
                .enqueue(object :retrofit2.Callback<JsonObject>{
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>
                    ) {
                        Log.d("dbdjh", "onResponse: "+response)
                        Log.d("hdfgdhf", "onResponse: "+response.body())
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })
        }

    }
}


