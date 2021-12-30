package com.example.recyclerviewkotlinapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewkotlinapplication.R
import com.example.recyclerviewkotlinapplication.data.Post
import androidx.databinding.DataBindingUtil
import com.example.recyclerviewkotlinapplication.databinding.RowDataBinding

class PostAdapter(val context: Context, var postList: ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowDataBinding: RowDataBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_data, parent, false)
        return PostViewHolder(rowDataBinding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position], position)
    }

    override fun getItemCount(): Int = postList.size


    inner class PostViewHolder(val rowDataBinding: RowDataBinding) :
        RecyclerView.ViewHolder(rowDataBinding.root) {

        fun bind(post: Post, i: Int) {
            this.rowDataBinding.post = post
            this.rowDataBinding.executePendingBindings()
        }
    }

    fun setPostData(postList: ArrayList<Post>) {
        this.postList = postList
        notifyDataSetChanged()
    }

}