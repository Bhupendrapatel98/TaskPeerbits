package com.example.recyclerviewkotlinapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewkotlinapplication.R
import com.example.recyclerviewkotlinapplication.data.Post
import androidx.databinding.DataBindingUtil
import com.example.recyclerviewkotlinapplication.databinding.RowDataBinding
import com.example.recyclerviewkotlinapplication.databinding.UserDataBinding
import com.example.recyclerviewkotlinapplication.room.User

class UserAdapter(val context: Context, var userlist: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val userDataBinding: UserDataBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.user_data, parent, false)
        return UserViewHolder(userDataBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userlist[position], position)
    }

    override fun getItemCount(): Int = userlist.size


    inner class UserViewHolder(val userDataBinding: UserDataBinding) :
        RecyclerView.ViewHolder(userDataBinding.root) {

        fun bind(user: User, i: Int) {
            this.userDataBinding.user = user
            this.userDataBinding.executePendingBindings()
        }
    }

    fun setPostData(userlist: ArrayList<User>) {
        this.userlist = userlist
        notifyDataSetChanged()
    }

}